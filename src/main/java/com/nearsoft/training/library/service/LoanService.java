package com.nearsoft.training.library.service;

public interface LoanService {
    void lendBooks(String[] isbnList);

    void returnBooks(String[] isbnList);
}
