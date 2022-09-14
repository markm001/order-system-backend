package com.ccat.ordersys.exceptions;

import org.springframework.http.HttpStatus;

public class OrderSystemException extends Exception{
    private String message;
    private HttpStatus httpStatus;

    public OrderSystemException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
