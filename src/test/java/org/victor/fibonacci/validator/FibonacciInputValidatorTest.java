package org.victor.fibonacci.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.victor.fibonacci.util.exception.InvalidInputException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FibonacciInputValidatorTest {
    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "The Fibonacci index 'n' must be a positive integer.";
    private static final String INVALID_LENGTH_EXCEPTION_MESSAGE = "The Fibonacci index 'n' must be 3 digits max.";

    @Autowired
    private FibonacciInputValidator classUnderTest;

    @ParameterizedTest
    @MethodSource("getNonPositiveIntegerTestData")
    void givenNonPositiveIntegerObjectInputWhenValidateInputThenThrowException(String input) {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> classUnderTest.validateFibonacciInput(input, ""));
        assertNotNull(exception);
        assertEquals(INVALID_INPUT_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void givenLongerPositiveIntegerWhenValidateInputThenThrowException() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> classUnderTest.validateFibonacciInput("1234", ""));
        assertNotNull(exception);
        assertEquals(INVALID_LENGTH_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void givenPositiveIntegerInputWhenValidateInputThenDoNotThrowException() {
        assertDoesNotThrow(() -> classUnderTest.validateFibonacciInput("2", ""),
                "When called with a positive integer, Fibonacci input validation should not fail");
    }

    @Test
    void givenLongerStringInputWhenValidateInputThenThrowException() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> classUnderTest.validateFibonacciInput("string", ""));
        assertNotNull(exception);
        assertEquals(INVALID_LENGTH_EXCEPTION_MESSAGE + INVALID_INPUT_EXCEPTION_MESSAGE,
                exception.getMessage());
    }

    private static Stream<String> getNonPositiveIntegerTestData() {
        return Stream.of("str", "1.1", "-1");
    }
}
