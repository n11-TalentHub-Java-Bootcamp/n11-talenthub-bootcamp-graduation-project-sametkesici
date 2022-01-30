package com.n11project.creditapplication.exception;

import org.springframework.http.HttpStatus;

public class PhoneNumberAlreadyExistException extends BaseException {

  public PhoneNumberAlreadyExistException() {
    super("Phone number already exist", HttpStatus.BAD_REQUEST.value());
  }

  public PhoneNumberAlreadyExistException(String message) {
    super(message);
  }
}