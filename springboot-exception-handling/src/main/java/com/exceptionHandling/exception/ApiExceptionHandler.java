package com.exceptionHandling.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //tum restcontroller için exception
public class ApiExceptionHandler {

    @ExceptionHandler({EntityNotFountException.class}) //sadece bu controller için çalışır.
    public String entityNotFound(){
        return "Record not found.";
    }

    @ExceptionHandler({IllegalArgumentException.class}) //sadece bu controller için çalışır.
    public String iaException(){
        return "Wrong Parameter.";
    }

}
