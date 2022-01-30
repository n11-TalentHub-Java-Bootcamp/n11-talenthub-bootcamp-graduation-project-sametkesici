package com.n11project.creditapplication.exception;

import org.springframework.http.HttpStatus;

public class PhoneNumberIsNotValidException extends BaseException {

  public PhoneNumberIsNotValidException() {
    super("Phone is not suitable for receiving messages.You can update it from the Update tab.Example = 905389814433",
          HttpStatus.NOT_ACCEPTABLE.value());
  }

  public PhoneNumberIsNotValidException(String message) {
    super(message);
  }
}