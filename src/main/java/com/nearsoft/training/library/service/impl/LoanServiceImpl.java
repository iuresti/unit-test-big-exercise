package com.nearsoft.training.library.service.impl;

import com.nearsoft.training.library.config.LoanConfigurationProperties;
import com.nearsoft.training.library.exception.LoanNotAllowedException;
import com.nearsoft.training.library.model.Book;
import com.nearsoft.training.library.model.BooksByUser;
import com.nearsoft.training.library.model.User;
import com.nearsoft.training.library.repository.BookRepository;
import com.nearsoft.training.library.service.CardReaderService;
import com.nearsoft.training.library.service.LoanService;
import com.nearsoft.training.library.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    private CardReaderService cardReaderService;

    private UserService userService;

    private LoanConfigurationProperties loanConfigurationProperties;

    private BookRepository bookRepository;

    public LoanServiceImpl(CardReaderService cardReaderService,
                           UserService userService,
                           LoanConfigurationProperties loanConfigurationProperties,
                           BookRepository bookRepository) {
        this.cardReaderService = cardReaderService;
        this.userService = userService;
        this.loanConfigurationProperties = loanConfigurationProperties;
        this.bookRepository = bookRepository;
    }

    @Override
    public void lendBooks(String[] isbnList) {
        User user = cardReaderService.readUser();

        validateLoan(user, isbnList);

        userService.registerLoan(user, isbnList);
    }

    @Override
    public void returnBooks(String[] isbnList) {
        User user = cardReaderService.readUser();

        userService.registerReturn(user, isbnList);
    }

    void validateLoan(User user, String[] isbnList) {

        Set<BooksByUser> borrowedBooks = userService.getBorrowedBooks(user.getCurp());
        LocalDate now = LocalDate.now();
        LocalDate oneMonthAgo = now.minus(1, ChronoUnit.MONTHS);

        Set<String> borrowedBooksOnTime = borrowedBooks.stream()
                .filter(booksByUser -> booksByUser.getBorrowDate().isAfter(oneMonthAgo))
                .map(BooksByUser::getIsbn)
                .collect(Collectors.toSet());

        if (borrowedBooksOnTime.size() < borrowedBooks.size()) {
            throw new LoanNotAllowedException("User must return old loans");
        }

        for (String isbn : isbnList) {

            if (borrowedBooksOnTime.contains(isbn)) {
                throw new LoanNotAllowedException("Attempt to borrow more than once a book");
            }

            borrowedBooksOnTime.add(isbn);

            if (!bookRepository.findById(isbn).isPresent()) {
                Book book = new Book();

                book.setIsbn(isbn);

                bookRepository.save(book);
            }

        }


        if (borrowedBooks.size() >= loanConfigurationProperties.getMaxBooksPerUser()) {
            throw new LoanNotAllowedException("User has reached the max number of books allowed");
        }

    }
}
