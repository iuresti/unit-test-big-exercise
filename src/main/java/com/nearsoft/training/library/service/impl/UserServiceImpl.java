package com.nearsoft.training.library.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nearsoft.training.library.model.BooksByUser;
import com.nearsoft.training.library.model.User;
import com.nearsoft.training.library.repository.BooksByUserRepository;
import com.nearsoft.training.library.repository.UserRepository;
import com.nearsoft.training.library.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private BooksByUserRepository booksByUserRepository;

    public UserServiceImpl(UserRepository userRepository, BooksByUserRepository booksByUserRepository) {
        this.userRepository = userRepository;
        this.booksByUserRepository = booksByUserRepository;
    }

    @Transactional
    @Override
    public void registerLoan(User user, String[] isbnList) {
        if(!userRepository.findById(user.getCurp()).isPresent()){
            userRepository.save(user);
        }

        for(String isbn : isbnList){
            if(!booksByUserRepository.findByIsbnAndCurp(isbn, user.getCurp()).isPresent()){
                BooksByUser booksByUser = new BooksByUser();

                booksByUser.setBorrowDate(LocalDate.now());
                booksByUser.setCurp(user.getCurp());
                booksByUser.setIsbn(isbn);

                booksByUserRepository.save(booksByUser);
            }
        }
    }

    @Transactional
    @Override
    public void registerReturn(User user, String[] isbnList) {
        if(!userRepository.findById(user.getCurp()).isPresent()){
            userRepository.save(user);
        }

        booksByUserRepository.deleteByCurpAndIsbnIn(user.getCurp(), isbnList);
    }

    @Override
    public Set<BooksByUser> getBorrowedBooks(String curp) {
        return booksByUserRepository.findByCurp(curp);
    }
}
