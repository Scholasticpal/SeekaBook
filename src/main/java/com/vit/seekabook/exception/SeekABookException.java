package com.vit.seekabook.exception;

public class SeekABookException extends Exception {

    public SeekABookException() {
        super();
    }

    public SeekABookException(String message) {
        super(message);
    }

    public SeekABookException(Throwable throwable) {
        super(throwable);
    }

    public SeekABookException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
