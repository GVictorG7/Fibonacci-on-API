package org.victor.fibonacci.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FibonacciService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciService.class);
    private final Map<Integer, Long> cache;
    private Integer maxCalculatedIndex;

    @Autowired
    public FibonacciService(Map<Integer, Long> fibonacciCache) {
        this.cache = fibonacciCache;
        this.maxCalculatedIndex = 1;
    }

    public long calculateFibonacciNumber(int n) {
        LOGGER.info("Starting calculating fibonacci number for n = {}", n);
        if (cache.containsKey(n)) {
            LOGGER.info("Fibonacci number is already calculated in cache.");
            return cache.get(n);
        }
        calculateFibonacciNumberFromMaxIndex(n);
        return cache.get(n);
    }

    private void calculateFibonacciNumberFromMaxIndex(int n) {
        LOGGER.info("Calculating Fibonacci number n = {} from the last calculated index = {}", n, maxCalculatedIndex);
        long a = cache.get(maxCalculatedIndex - 1);
        long b = cache.get(maxCalculatedIndex);

        for (int index = maxCalculatedIndex + 1; index <= n; index++) {
            long fib = a + b;
            LOGGER.info("Caching Fibonacci number = {} at index = {}", fib, index);
            cache.put(index, fib);
            a = b;
            b = fib;
            maxCalculatedIndex = index;
            LOGGER.info("New max calculated index = {}", maxCalculatedIndex);
        }
    }
}
