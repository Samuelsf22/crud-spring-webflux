package com.crud.crud_spring_webflux.exception.domain;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

    private HttpStatus httpStatus;

    public CustomException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
}
