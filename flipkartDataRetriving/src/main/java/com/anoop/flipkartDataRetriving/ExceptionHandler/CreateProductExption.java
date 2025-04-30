package com.anoop.flipkartDataRetriving.ExceptionHandler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CreateProductExption {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e){
        String error = "Validation failed: " +  e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    // @ExceptionHandler(IOException.class)
    // public ResponseEntity<String> handleIOException(IOException exception){
    //     String error = exception.getMessage();
    //     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    // }

    
}
