package com.nearsoft.training.library.service.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.mockito.Mockito;

import com.nearsoft.training.library.config.LoanConfigurationProperties;
import com.nearsoft.training.library.exception.LoanNotAllowedException;
import com.nearsoft.training.library.model.Book;
import com.nearsoft.training.library.model.BooksByUser;
import com.nearsoft.training.library.model.User;
import com.nearsoft.training.library.repository.BookRepository;
import com.nearsoft.training.library.service.CardReaderService;
import com.nearsoft.training.library.service.UserService;

public class LoanServiceImplTest {

    @Test
    public void givenAUserWithNoLoans_whenLendBooks_thenBooksAreLent() {
        // Given:
        CardReaderService cardReaderService = Mockito.mock(CardReaderService.class);
        UserService userService = Mockito.mock(UserService.class);
        LoanConfigurationProperties loanConfigurationProperties = new LoanConfigurationProperties();
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        LoanServiceImpl loanService = new LoanServiceImpl(cardReaderService, userService, loanConfigurationProperties, bookRepository);
        String[] isbnList = {"ABC", "DEF"};
        User user = new User();
        String curp = UUID.randomUUID().toString();
        Set<BooksByUser> borrowedBooks = new HashSet<>();
        Book abcBook = new Book();

        user.setCurp(curp);

        Mockito.when(cardReaderService.readUser()).thenReturn(user);
        Mockito.when(userService.getBorrowedBooks(curp)).thenReturn(borrowedBooks);
        Mockito.when(bookRepository.findById("ABC")).thenReturn(Optional.of(abcBook));
        Mockito.when(bookRepository.findById("DEF")).thenReturn(Optional.empty());

        loanConfigurationProperties.setMaxBooksPerUser(2);

        // When:
        loanService.lendBooks(isbnList);

        // Then:
    }

    @Test
    public void givenAUserWithLoans_whenLendBooksForAnExistentBook_thenThrowException() {
        // Given:
        CardReaderService cardReaderService = Mockito.mock(CardReaderService.class);
        UserService userService = Mockito.mock(UserService.class);
        LoanConfigurationProperties loanConfigurationProperties = new LoanConfigurationProperties();
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        LoanServiceImpl loanService = new LoanServiceImpl(cardReaderService, userService, loanConfigurationProperties, bookRepository);
        String[] isbnList = {"ABC", "DEF"};
        User user = new User();
        String curp = UUID.randomUUID().toString();
        Set<BooksByUser> borrowedBooks = new HashSet<>();
        Book abcBook = new Book();
        BooksByUser booksByUserForABC = new BooksByUser();
        LocalDate now = LocalDate.now();

        user.setCurp(curp);

        booksByUserForABC.setCurp(curp);
        booksByUserForABC.setIsbn("ABC");
        booksByUserForABC.setBorrowDate(now.minusDays(15));

        borrowedBooks.add(booksByUserForABC);

        Mockito.when(cardReaderService.readUser()).thenReturn(user);
        Mockito.when(userService.getBorrowedBooks(curp)).thenReturn(borrowedBooks);
        Mockito.when(bookRepository.findById("ABC")).thenReturn(Optional.of(abcBook));
        Mockito.when(bookRepository.findById("DEF")).thenReturn(Optional.empty());

        loanConfigurationProperties.setMaxBooksPerUser(2);

        // When:
        assertThatThrownBy(() -> loanService.lendBooks(isbnList)).isInstanceOf(LoanNotAllowedException.class).hasMessage("Attempt to borrow more than once a book");

    }

}
