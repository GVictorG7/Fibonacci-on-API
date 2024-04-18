package org.victor.fibonacci.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FibonacciConfig {

    @Bean
    public Map<Integer, Long> fibonacciCache() {
        Map<Integer, Long> fibonacciCache = new HashMap<>();
        fibonacciCache.put(0, 0L);
        fibonacciCache.put(1, 1L);
        return fibonacciCache;
    }
}
