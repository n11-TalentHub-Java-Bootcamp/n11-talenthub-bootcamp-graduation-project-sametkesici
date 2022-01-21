package com.n11project.creditapplication.exception;

import com.n11project.creditapplication.dto.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CreditApplicationControllerAdvice {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(Exception exception){
        BaseException baseException = (BaseException) exception;
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(baseException.getMessage());
        errorResponse.setType(exception.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse,new HttpHeaders(), baseException.getHttpStatus());
    }
}
