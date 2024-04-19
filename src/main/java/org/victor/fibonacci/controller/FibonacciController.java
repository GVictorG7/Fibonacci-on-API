package org.victor.fibonacci.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.victor.fibonacci.service.FibonacciService;
import org.victor.fibonacci.validation.FibonacciInputValidator;

@RestController
@RequiredArgsConstructor
public class FibonacciController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciController.class);
    private final FibonacciService service;
    private final FibonacciInputValidator validator;

    @GetMapping("/fib")
    public ResponseEntity<String> calculateFibonacci(@RequestParam String n) {
        LOGGER.info("Fibonacci number calculation requested for n = {}", n);
        validator.validateFibonacciInput(n);
        int intN = Integer.parseInt(n);
        long fib = service.calculateFibonacciNumber(intN);

        LOGGER.info("Fibonacci number calculated for n = {} is fib = {}. Sending response.", intN, fib);
        return ResponseEntity.ok(Long.toString(fib));
    }
}
