package com.n11project.creditapplication.exception;

import org.springframework.http.HttpStatus;

public class CustomerAlreadyExistException extends BaseException{
    public CustomerAlreadyExistException() {
        super("User not found!", HttpStatus.BAD_REQUEST.value());
    }

    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
