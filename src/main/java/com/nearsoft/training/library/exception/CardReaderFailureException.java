package com.nearsoft.training.library.exception;

public class CardReaderFailureException extends RuntimeException {

    private static final long serialVersionUID = -1256827925128492245L;

    public CardReaderFailureException(Throwable e) {
        super(e);
    }
}
