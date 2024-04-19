package org.victor.fibonacci.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.victor.fibonacci.util.exception.InvalidInputException;

/**
 * Validator class for the input passed by the user
 */
@Component
public class FibonacciInputValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciInputValidator.class);
    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "The Fibonacci index 'n' must be a positive integer.";
    private static final String INVALID_LENGTH_EXCEPTION_MESSAGE = "The Fibonacci index 'n' must be %d digits max.";

    @Value("${fibonacci.max-request-param-length:3}")
    private int maxRequestParamLength;

    /**
     * Validate the Fibonacci sequence index to be a non-negative integer value.
     *
     * @param n             the Fibonacci sequence index as String to be validated
     * @param correlationId the unique identification id of the request for logging purposes
     */
    public void validateFibonacciInput(String n, String correlationId) {
        LOGGER.info("Validating Fibonacci index '{}'. correlationId={}", n, correlationId);

        StringBuilder stringBuilder = new StringBuilder();

        if (n.length() > maxRequestParamLength) {
            LOGGER.error("Fibonacci index 'n' cannot be longer than {} digits. correlationId={}", maxRequestParamLength,
                    correlationId);
            stringBuilder.append(INVALID_LENGTH_EXCEPTION_MESSAGE.formatted(maxRequestParamLength));
        }

        try {
            int intN = Integer.parseInt(n);
            if (intN < 0) {
                LOGGER.error("Fibonacci index 'n' cannot be negative. correlationId={}", correlationId);
                stringBuilder.append(INVALID_INPUT_EXCEPTION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            // not including the exception in the log to suppress printing the stack trace
            LOGGER.error("Fibonacci index 'n' must be a number. correlationId={}", correlationId);
            stringBuilder.append(INVALID_INPUT_EXCEPTION_MESSAGE);
        }

        if (!stringBuilder.isEmpty()) {
            throw new InvalidInputException(stringBuilder.toString(), correlationId);
        }

        LOGGER.info("Fibonacci index 'n' successfully validated. correlationId={}", correlationId);
    }
}
