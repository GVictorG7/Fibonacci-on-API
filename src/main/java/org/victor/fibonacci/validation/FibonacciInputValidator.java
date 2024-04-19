package org.victor.fibonacci.validation;

import org.springframework.stereotype.Component;
import org.victor.fibonacci.util.exception.InvalidInputException;

@Component
public class FibonacciInputValidator {
    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "The Fibonacci index 'n' must be a positive integer";

    public void validateFibonacciInput(String n) {
        try {
            int intN = Integer.parseInt(n);
            if (intN < 0) {
                throw new InvalidInputException(INVALID_INPUT_EXCEPTION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            throw new InvalidInputException(INVALID_INPUT_EXCEPTION_MESSAGE);
        }
    }
}
