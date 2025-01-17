package com.imt.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"com.imt.demo.controller"})
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<String> manageRuntimeException() {
        return ResponseEntity.internalServerError().build();
    }
    
}
