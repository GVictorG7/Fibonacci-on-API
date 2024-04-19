package org.victor.fibonacci.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.victor.fibonacci.util.exception.InvalidInputException;

/**
 * {@link RestControllerAdvice} that handles the exceptions thrown from the application logic layer
 */
@RestControllerAdvice
public class FibonacciControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciControllerAdvice.class);

    /**
     * Handle the {@link InvalidInputException} and respond with code 400 - Bad Request
     *
     * @param ex the {@link InvalidInputException} object containing the error message
     * @return a {@link RestControllerAdvice} with code 400 - Bad Request and the body being the
     * exception message
     */
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException ex) {
        LOGGER.info("Invalid input is being handled: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
