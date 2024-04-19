package org.victor.fibonacci.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.victor.fibonacci.service.FibonacciService;
import org.victor.fibonacci.util.exception.InvalidInputException;
import org.victor.fibonacci.validation.FibonacciInputValidator;

@WebMvcTest(FibonacciController.class)
class FibonacciControllerTest {
    private static final String CALCULATE_FIBONACCI_GET_ENDPOINT = "/fib";
    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "The Fibonacci index 'n' must be a positive integer";

    @MockBean
    private FibonacciService service;
    @MockBean
    private FibonacciInputValidator validator;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenPositiveIntegerIndexWhenGetFibonacciThenReturnResultOk() throws Exception {
        // GIVEN
        when(service.getFibonacciNumber(5)).thenReturn(5L);

        // WHEN
        mockMvc.perform(get(CALCULATE_FIBONACCI_GET_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("n", "5"))
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    void givenNonPositiveIntegerIndexWhenGetFibonacciThenReturnResultOk() throws Exception {
        // GIVEN
        InvalidInputException exception = new InvalidInputException(INVALID_INPUT_EXCEPTION_MESSAGE);
        doThrow(exception).when(validator).validateFibonacciInput(any());

        // WHEN
        mockMvc.perform(get(CALCULATE_FIBONACCI_GET_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("n", "anything"))
                // THEN
                .andExpect(status().isBadRequest())
                .andExpect(content().string(INVALID_INPUT_EXCEPTION_MESSAGE));
    }
}
