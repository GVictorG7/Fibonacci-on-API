package org.victor.fibonacci.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.victor.fibonacci.util.exception.InvalidInputException;

@Component
public class FibonacciInputValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciInputValidator.class);
    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "The Fibonacci index 'n' must be a positive integer";

    public void validateFibonacciInput(String n) {
        LOGGER.info("Validating Fibonacci index '{}'", n);
        try {
            int intN = Integer.parseInt(n);
            if (intN < 0) {
                LOGGER.warn("Fibonacci index 'n' cannot be negative");
                throw new InvalidInputException(INVALID_INPUT_EXCEPTION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            // not including the exception in the log to suppress printing the stack trace
            LOGGER.warn("Fibonacci index 'n' must be a number");
            throw new InvalidInputException(INVALID_INPUT_EXCEPTION_MESSAGE);
        }
        LOGGER.info("Fibonacci index 'n' successfully validated");
    }
}
