package org.victor.fibonacci.controller.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.victor.fibonacci.controller.FibonacciController;

@SpringBootTest
class FibonacciControllerIntegrationTest {
    private static final String CALCULATE_FIBONACCI_GET_ENDPOINT = "/fib";
    private MockMvc mockMvc;

    @Autowired
    private FibonacciController classUnderTest;

    @BeforeEach
    void setUpClass() {
        mockMvc = MockMvcBuilders.standaloneSetup(classUnderTest).build();
    }

    @Test
    void givenPositiveIndexWhenGetFibonacciThenReturnResultOk() throws Exception {
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
