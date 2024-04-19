package org.victor.fibonacci.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class that holds the definition of beans
 */
@Configuration
public class FibonacciConfig {

    /**
     * Creates a {@link Bean} representing the Fibonacci series cache initialized with the first 2
     * root values
     *
     * @return a {@link Map} holding the fist 2 root values from the Fibonacci series
     */
    @Bean
    public Map<Integer, Long> fibonacciCache() {
        Map<Integer, Long> fibonacciCache = new HashMap<>();
        fibonacciCache.put(0, 0L);
        fibonacciCache.put(1, 1L);
        return fibonacciCache;
    }
}
