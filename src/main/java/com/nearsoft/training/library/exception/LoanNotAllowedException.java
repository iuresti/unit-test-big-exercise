package com.nearsoft.training.library.exception;

public class LoanNotAllowedException extends RuntimeException {

    public LoanNotAllowedException(String message) {
        super(message);
    }
}
