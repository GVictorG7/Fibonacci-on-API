package org.victor.fibonacci.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.victor.fibonacci.util.exception.InvalidInputException;

@RestControllerAdvice
public class FibonacciControllerAdvice {
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
