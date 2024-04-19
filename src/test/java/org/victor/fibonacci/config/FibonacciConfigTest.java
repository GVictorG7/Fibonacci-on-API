package org.victor.fibonacci.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciConfigTest {
    private FibonacciConfig classUnderTest;
    
    @BeforeEach
    void setUpClass() {
        classUnderTest = new FibonacciConfig();
    }

    @Test
    void testFibonacciCache() {
        // WHEN
        Map<Integer, BigInteger> fibonacciCache = classUnderTest.fibonacciCache();

        // THEN
        assertEquals(2, fibonacciCache.size());
        assertEquals(BigInteger.ZERO, fibonacciCache.get(0));
        assertEquals(BigInteger.ONE, fibonacciCache.get(1));
    }
}
