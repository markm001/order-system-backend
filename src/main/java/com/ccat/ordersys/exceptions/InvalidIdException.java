package com.ccat.ordersys.exceptions;

import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class InvalidIdException implements Supplier<RuntimeException> {

    private String message;
    private HttpStatus httpStatus;

    public InvalidIdException(String message) {
        this.message = message;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    @Override
    public RuntimeException get() {
        return new RuntimeException();
    }
}
