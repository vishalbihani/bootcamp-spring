package com.bootcamp.spring.basics;

import com.bootcamp.spring.basics.controller.LoginController;
import com.bootcamp.spring.basics.exchange.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ResponseBody> handleUserNotFoundException(UserNotFoundException e) {

        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ResponseBody> handleUserNotFoundException(IllegalArgumentException e) {

        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
