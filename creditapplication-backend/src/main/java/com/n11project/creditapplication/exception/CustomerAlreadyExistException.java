package com.n11project.creditapplication.exception;

import org.springframework.http.HttpStatus;

public class CustomerAlreadyExistException extends BaseException {

  public CustomerAlreadyExistException() {
    super("Customer already exist!", HttpStatus.BAD_REQUEST.value());
  }

  public CustomerAlreadyExistException(String message) {
    super(message);
  }
}