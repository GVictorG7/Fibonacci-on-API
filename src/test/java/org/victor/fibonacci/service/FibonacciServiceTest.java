package org.victor.fibonacci.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FibonacciServiceTest {
    private FibonacciService classUnderTest;
    private Map<Integer, BigInteger> fibonacciCache;

    @BeforeEach
    void setUpClass() {
        fibonacciCache = spy(new HashMap<>());
        fibonacciCache.put(0, BigInteger.ZERO);
        fibonacciCache.put(1, BigInteger.ONE);
        classUnderTest = new FibonacciService(fibonacciCache);
    }

    @Test
    void givenNewFibonacciServiceThenCacheHasTheTwoRootValues() {
        // asserting the state of the service's cache after initialization
        assertEquals(2, fibonacciCache.size());
        assertEquals(BigInteger.ZERO, fibonacciCache.get(0));
        assertEquals(BigInteger.ONE, fibonacciCache.get(1));
    }

    @Test
    void givenIndexInInitialCacheWhenCalculateFibonacciThenReturnCachedValue() {
        assertEquals(BigInteger.ZERO, classUnderTest.getFibonacciNumber(0, ""));
        verify(fibonacciCache).containsKey(0);
        verify(fibonacciCache).get(0);
        assertEquals(BigInteger.ONE, classUnderTest.getFibonacciNumber(1, ""));
        verify(fibonacciCache).containsKey(1);
        verify(fibonacciCache).get(1);
    }

    @Test
    void givenIndexNotInCacheWhenCalculateFibonacciThenCalculate() {
        assertEquals(BigInteger.valueOf(8), classUnderTest.getFibonacciNumber(6, ""));
        verify(fibonacciCache).containsKey(6);
        verify(fibonacciCache).get(0);
        verify(fibonacciCache).get(1);
        verify(fibonacciCache).putIfAbsent(2, BigInteger.ONE);
        verify(fibonacciCache).putIfAbsent(3, BigInteger.TWO);
        verify(fibonacciCache).putIfAbsent(4, BigInteger.valueOf(3));
        verify(fibonacciCache).putIfAbsent(5, BigInteger.valueOf(5));
        verify(fibonacciCache).putIfAbsent(6, BigInteger.valueOf(8));
    }

    @Test
    void givenIndexNotInCacheAndIntermediateCalculationWhenCalculateFibonacciThenCalculateFromMaxIndex() {
        // GIVEN
        classUnderTest.getFibonacciNumber(6, "");

        // THEN
        assertEquals(BigInteger.valueOf(21), classUnderTest.getFibonacciNumber(8, ""));
        verify(fibonacciCache).containsKey(8);
        verify(fibonacciCache).get(5);
        // one time from the GIVEN invocation and one from the THEN invocation
        verify(fibonacciCache, times(2)).get(6);
        verify(fibonacciCache).putIfAbsent(7, BigInteger.valueOf(13));
        verify(fibonacciCache).putIfAbsent(8, BigInteger.valueOf(21));
    }
}
