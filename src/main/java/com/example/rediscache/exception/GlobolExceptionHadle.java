package com.example.rediscache.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobolExceptionHadle {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity <ProductNotFoundException> handleProductNotFoundException (ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);

    }
}
