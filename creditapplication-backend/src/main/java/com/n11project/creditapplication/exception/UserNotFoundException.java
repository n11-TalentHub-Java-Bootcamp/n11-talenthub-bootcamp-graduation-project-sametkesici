package com.n11project.creditapplication.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException{
    public UserNotFoundException(String message, int httpStatus) {
        super("User not found!", HttpStatus.BAD_REQUEST.value());
    }

    public UserNotFoundException(String message) {
        super(message,HttpStatus.BAD_REQUEST.value());
    }
}
