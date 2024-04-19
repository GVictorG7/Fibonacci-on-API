package org.victor.fibonacci.util.exception;

import lombok.Getter;

/**
 * Custom exception that in thrown in case the input passed by the user is not compliant
 */
@Getter
public class InvalidInputException extends RuntimeException {
    private final String correlationId;

    /**
     * Constructor initializing the error message
     *
     * @param message       the error message
     * @param correlationId the unique identification id of the request for logging purposes
     */
    public InvalidInputException(String message, String correlationId) {
        super(message);
        this.correlationId = correlationId;
    }
}
