package org.victor.fibonacci.util.exception;

/**
 * Custom exception that in thrown in case the input passed by the user is not compliant
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Constructor initializing the error message
     *
     * @param message the error message
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
