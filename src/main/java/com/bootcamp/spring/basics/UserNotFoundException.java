package com.bootcamp.spring.basics;

public class UserNotFoundException extends Exception {

    private final String message;

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public UserNotFoundException(String message, Throwable t) {
        super(message, t);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
