package com.n11project.creditapplication.exception;

import org.springframework.http.HttpStatus;

public class InputMismatchException extends BaseException {

  public InputMismatchException() {
    super("inputs are mismatch", HttpStatus.BAD_REQUEST.value());
  }

  public InputMismatchException(String message) {
    super(message);
  }
}