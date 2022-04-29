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
    public ResponseEntity<ResponseBody> handleUserNotFoundException() {

        return new ResponseEntity<>(
                new ResponseBody(LoginController.STATUS_NOT_FOUND, LoginController.USER_NOT_FOUND),
                HttpStatus.NOT_FOUND
        );
    }
}