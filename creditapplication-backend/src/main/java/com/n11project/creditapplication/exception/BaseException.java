package com.n11project.creditapplication.exception;

import liquibase.pro.packaged.A;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException{

    private final String message;

    private final int httpStatus;

    public BaseException(String message){
        super(message);
        this.message = message;
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
