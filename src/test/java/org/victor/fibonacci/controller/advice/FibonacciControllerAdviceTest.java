package org.victor.fibonacci.controller.advice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.victor.fibonacci.util.exception.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FibonacciControllerAdviceTest {
    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "The Fibonacci index 'n' must be a positive integer";

    private FibonacciControllerAdvice classUnderTest;

    @BeforeEach
    public void setUpClass() {
        classUnderTest = new FibonacciControllerAdvice();
    }

    @Test
    void givenInvalidInputExceptionWhenHandlingThenBadRequest() {
        // GIVEN
        InvalidInputException ex = new InvalidInputException(INVALID_INPUT_EXCEPTION_MESSAGE, "correlationId");

        // WHEN
        ResponseEntity<String> result = classUnderTest.handleInvalidInputException(ex);

        // THEN
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(INVALID_INPUT_EXCEPTION_MESSAGE, result.getBody());
    }
}
