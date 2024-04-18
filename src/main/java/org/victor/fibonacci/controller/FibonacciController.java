package org.victor.fibonacci.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.victor.fibonacci.service.FibonacciService;

@RestController
@RequiredArgsConstructor
public class FibonacciController {
    private final FibonacciService service;

    @GetMapping("/fib")
    public ResponseEntity<String> calculateFibonacci(@RequestParam Integer n) {
        long fib = service.calculateFibonacciNumber(n);

        return ResponseEntity.ok(Long.toString(fib));
    }
}
