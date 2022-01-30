package com.n11project.creditapplication.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends BaseException {

  public CustomerNotFoundException() {
    super("Customer not found!", HttpStatus.BAD_REQUEST.value());
  }

  public CustomerNotFoundException(String message) {
    super(message, HttpStatus.BAD_REQUEST.value());
  }
}