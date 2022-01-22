package com.n11project.creditapplication.exception;

import org.springframework.http.HttpStatus;

public class StrategyNotFoundException extends BaseException{

    public StrategyNotFoundException() {
        super("Strategy not found!", HttpStatus.BAD_REQUEST.value());
    }

    public StrategyNotFoundException(String message) {
        super(message);
    }
}
