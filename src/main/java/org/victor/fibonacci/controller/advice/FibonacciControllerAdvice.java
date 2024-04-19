package org.victor.fibonacci.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.victor.fibonacci.util.exception.InvalidInputException;

@RestControllerAdvice
public class FibonacciControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciControllerAdvice.class);

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException ex) {
        LOGGER.info("Invalid input is being handled: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
