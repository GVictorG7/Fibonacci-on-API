package org.victor.fibonacci.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.victor.fibonacci.util.exception.InvalidInputException;

class FibonacciInputValidatorTest {
    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "The Fibonacci index 'n' must be a positive integer";

    private FibonacciInputValidator classUnderTest;

    @BeforeEach
    void setUpClass() {
        classUnderTest = new FibonacciInputValidator();
    }

    @ParameterizedTest
    @MethodSource("getNonPositiveIntegerTestData")
    void givenNonPositiveIntegerObjectInputWhenValidateInputThenThrowException(String input) {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> classUnderTest.validateFibonacciInput(input, ""));
        assertNotNull(exception);
        assertEquals(INVALID_INPUT_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void givenPositiveIntegerInputWhenValidateInputThenDoNotThrowException() {
        assertDoesNotThrow(() -> classUnderTest.validateFibonacciInput("2", ""),
                "When called with a positive integer, Fibonacci input validation should not fail");
    }

    private static Stream<String> getNonPositiveIntegerTestData() {
        return Stream.of("string", "1.1", "-1");
    }
}
