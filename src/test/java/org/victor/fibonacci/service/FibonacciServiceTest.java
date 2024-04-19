package org.victor.fibonacci.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FibonacciServiceTest {
    private FibonacciService classUnderTest;
    private Map<Integer, Long> fibonacciCache;

    @BeforeEach
    void setUpClass() {
        fibonacciCache = spy(new HashMap<>());
        fibonacciCache.put(0, 0L);
        fibonacciCache.put(1, 1L);
        classUnderTest = new FibonacciService(fibonacciCache);
    }

    @Test
    void givenNewFibonacciServiceThenCacheHasTheTwoRootValues() {
        // asserting the state of the service's cache after initialization
        assertEquals(2, fibonacciCache.size());
        assertEquals(0, fibonacciCache.get(0));
        assertEquals(1, fibonacciCache.get(1));
    }

    @Test
    void givenIndexInInitialCacheWhenCalculateFibonacciThenReturnCachedValue() {
        assertEquals(0L, classUnderTest.getFibonacciNumber(0));
        verify(fibonacciCache).containsKey(0);
        verify(fibonacciCache).get(0);
        assertEquals(1L, classUnderTest.getFibonacciNumber(1));
        verify(fibonacciCache).containsKey(1);
        verify(fibonacciCache).get(1);
    }

    @Test
    void givenIndexNotInCacheWhenCalculateFibonacciThenCalculate() {
        assertEquals(8L, classUnderTest.getFibonacciNumber(6));
        verify(fibonacciCache).containsKey(6);
        verify(fibonacciCache).get(0);
        verify(fibonacciCache).get(1);
        verify(fibonacciCache).put(2, 1L);
        verify(fibonacciCache).put(3, 2L);
        verify(fibonacciCache).put(4, 3L);
        verify(fibonacciCache).put(5, 5L);
        verify(fibonacciCache).put(6, 8L);
    }

    @Test
    void givenIndexNotInCacheAndIntermediateCalculationWhenCalculateFibonacciThenCalculateFromMaxIndex() {
        // GIVEN
        classUnderTest.getFibonacciNumber(6);

        // THEN
        assertEquals(21L, classUnderTest.getFibonacciNumber(8));
        verify(fibonacciCache).containsKey(8);
        verify(fibonacciCache).get(5);
        // one time from the GIVEN invocation and one from the THEN invocation
        verify(fibonacciCache, times(2)).get(6);
        verify(fibonacciCache).put(7, 13L);
        verify(fibonacciCache).put(8, 21L);
    }
}
