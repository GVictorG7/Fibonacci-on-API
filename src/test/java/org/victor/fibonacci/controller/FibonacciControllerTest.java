package org.victor.fibonacci.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.victor.fibonacci.service.FibonacciService;

@WebMvcTest(FibonacciController.class)
class FibonacciControllerTest {
    private static final String CALCULATE_FIBONACCI_GET_ENDPOINT = "/fib";

    @MockBean
    private FibonacciService service;
    private MockMvc mockMvc;

    @BeforeEach
    void setUpClass() {
        FibonacciController classUnderTest = new FibonacciController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(classUnderTest).build();
    }

    @Test
    void givenPositiveIndexWhenGetFibonacciThenReturnResultOk() throws Exception {
        // GIVEN
        when(service.calculateFibonacciNumber(5)).thenReturn(5L);

        // WHEN
        mockMvc.perform(get(CALCULATE_FIBONACCI_GET_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("n", "5"))
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }
}
