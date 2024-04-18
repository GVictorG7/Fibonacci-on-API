package org.victor.fibonacci.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FibonacciService {
    private final Map<Integer, Long> cache;
    private Integer maxCalculatedIndex;

    @Autowired
    public FibonacciService(Map<Integer, Long> fibonacciCache) {
        this.cache = fibonacciCache;
        this.maxCalculatedIndex = 1;
    }

    public long calculateFibonacciNumber(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        calculateFibonacciNumberFromMaxIndex(n);
        return cache.get(n);
    }

    private void calculateFibonacciNumberFromMaxIndex(int n) {
        long a = cache.get(maxCalculatedIndex - 1);
        long b = cache.get(maxCalculatedIndex);

        for (int index = maxCalculatedIndex + 1; index <= n; index++) {
            long fib = a + b;
            cache.put(index, fib);
            a = b;
            b = fib;
            maxCalculatedIndex = index;
        }
    }
}
