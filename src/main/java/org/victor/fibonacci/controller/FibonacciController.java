package org.victor.fibonacci.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.victor.fibonacci.service.FibonacciService;
import org.victor.fibonacci.validation.FibonacciInputValidator;

@RestController
@RequiredArgsConstructor
public class FibonacciController {
    private final FibonacciService service;
    private final FibonacciInputValidator validator;

    @GetMapping("/fib")
    public ResponseEntity<String> calculateFibonacci(@RequestParam String n) {
        validator.validateFibonacciInput(n);
        int intN = Integer.parseInt(n);
        long fib = service.calculateFibonacciNumber(intN);

        return ResponseEntity.ok(Long.toString(fib));
    }
}
