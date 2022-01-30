package com.n11project.creditapplication.exception;

import org.springframework.http.HttpStatus;

public class ApplicationNotFoundException extends BaseException {

  public ApplicationNotFoundException() {
    super("Application not found!", HttpStatus.BAD_REQUEST.value());
  }

  public ApplicationNotFoundException(String message) {
    super(message);
  }
}