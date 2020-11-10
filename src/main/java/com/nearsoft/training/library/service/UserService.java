package com.nearsoft.training.library.service;

import java.util.Set;

import com.nearsoft.training.library.model.BooksByUser;
import com.nearsoft.training.library.model.User;

public interface UserService {
    void registerLoan(User user, String[] isbnList);

    void registerReturn(User user, String[] isbnList);

    Set<BooksByUser> getBorrowedBooks(String curp);
}
