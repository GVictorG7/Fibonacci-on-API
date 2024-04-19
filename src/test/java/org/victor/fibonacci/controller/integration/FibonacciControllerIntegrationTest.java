package org.victor.fibonacci.controller.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.victor.fibonacci.controller.FibonacciController;
import org.victor.fibonacci.controller.advice.FibonacciControllerAdvice;

@SpringBootTest
class FibonacciControllerIntegrationTest {
    private static final String CALCULATE_FIBONACCI_GET_ENDPOINT = "/fib";
    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "The Fibonacci index 'n' must be a positive integer.";
    private static final String INVALID_LENGTH_EXCEPTION_MESSAGE = "The Fibonacci index 'n' must be 3 digits max.";

    private MockMvc mockMvc;

    @Autowired
    private FibonacciController classUnderTest;
    @Autowired
    private FibonacciControllerAdvice controllerAdvice;

    @BeforeEach
    void setUpClass() {
        mockMvc = MockMvcBuilders.standaloneSetup(classUnderTest)
                .setControllerAdvice(controllerAdvice)
                .build();
    }

    @Test
    void givenPositiveIntegerIndexWhenGetFibonacciThenReturnResultOk() throws Exception {
        // WHEN
        mockMvc.perform(get(CALCULATE_FIBONACCI_GET_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("n", "5"))
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @ParameterizedTest
    @MethodSource("getNonPositiveIntegerTestData")
    void givenNonPositiveIntegerIndexWhenGetFibonacciThenReturnBadRequest(String input) throws Exception {
        // WHEN
        mockMvc.perform(get(CALCULATE_FIBONACCI_GET_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("n", input))
                // THEN
                .andExpect(status().isBadRequest())
                .andExpect(content().string(INVALID_INPUT_EXCEPTION_MESSAGE));
    }

    @Test
    void givenLongerStingIndexWhenGetFibonacciThenReturnBadRequest() throws Exception {
        // WHEN
        mockMvc.perform(get(CALCULATE_FIBONACCI_GET_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("n", "string"))
                // THEN
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        INVALID_LENGTH_EXCEPTION_MESSAGE + INVALID_INPUT_EXCEPTION_MESSAGE));
    }

    private static Stream<String> getNonPositiveIntegerTestData() {
        return Stream.of("str", "1.1", "-1");
    }
}
