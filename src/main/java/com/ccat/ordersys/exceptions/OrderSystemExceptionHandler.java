package com.ccat.ordersys.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@ControllerAdvice
public class OrderSystemExceptionHandler {

    //Handle all unhandled Exceptions before generating Response:
    @ExceptionHandler(value=OrderSystemException.class)
    public ResponseEntity<Info> handleErrors(HttpServletRequest request, OrderSystemException e) {

        System.out.println(e.getMessage());
        System.out.println(request.getRequestURI());

        Info info = new Info(e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(info, e.getHttpStatus());
    }

    //Handle all thrown Exceptions:
    @ExceptionHandler(value=Throwable.class)
    public ResponseEntity<Info> handleErrors(HttpServletRequest request, Throwable e) {

        System.out.println(e.getMessage());
        System.out.println(request.getRequestURI());

        Info info = new Info(
                e.getMessage().isBlank() ? "An error occurred." : e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(info, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static class Info {
        private final String message;
        private final String path;
        public Info(String msg, String path) {
            this.message = msg;
            this.path = path;
        }
        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }
    }
}
