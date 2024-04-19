package org.victor.fibonacci.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link Service} class containing the logic for calculating the Fibonacci sequence numbers
 */
@Service
public class FibonacciService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciService.class);
    private final Map<Integer, BigInteger> cache;
    private final AtomicInteger maxCalculatedIndex;

    /**
     * Controller that injects the cache and sets the starting 'maxCalculatedIndex' field value to 1
     *
     * @param fibonacciCache a {@link Map} containing the first 2 root staring values from the Fibonacci sequence
     */
    @Autowired
    public FibonacciService(Map<Integer, BigInteger> fibonacciCache) {
        this.cache = fibonacciCache;
        this.maxCalculatedIndex = new AtomicInteger(1);
    }

    /**
     * Retrieves the Fibonacci sequence number corresponding to the index 'n' received as parameter.
     * If the values was calculated before and resides in cache, it will be fetched from the cache
     *
     * @param n             a non-negative integer value representing the Fibonacci sequence index
     * @param correlationId the unique identification id of the request for logging purposes
     * @return the Fibonacci sequence number corresponding the index 'n' received as parameter
     */
    public BigInteger getFibonacciNumber(int n, String correlationId) {
        LOGGER.info("Fetching Fibonacci number for n = {}. correlationId={}", n, correlationId);
        if (!cache.containsKey(n)) {
            calculateFibonacciNumberFromMaxIndex(n, correlationId);
        } else {
            LOGGER.info("Fibonacci number is already calculated in cache. correlationId={}", correlationId);
        }
        return cache.get(n);
    }

    /**
     * Calculate the Fibonacci sequence number corresponding to the index 'n' received as parameter.
     * The calculation starts from the last 'maxCalculatedIndex', using the last 2 values from the cache.
     * Each new Fibonacci number calculated is saved in the cache, and the 'maxCalculatedIndex' is updated.
     *
     * @param n             a non-negative integer value representing the Fibonacci sequence index
     * @param correlationId the unique identification id of the request for logging purposes
     */
    private void calculateFibonacciNumberFromMaxIndex(int n, String correlationId) {
        int lastCalculatedIndex = maxCalculatedIndex.get();
        LOGGER.info("Calculating Fibonacci number n = {} from the last calculated index = {}. correlationId={}",
                n, lastCalculatedIndex, correlationId);
        BigInteger a = cache.get(lastCalculatedIndex - 1);
        BigInteger b = cache.get(lastCalculatedIndex);

        for (int index = lastCalculatedIndex + 1; index <= n; index++) {
            BigInteger fib = a.add(b);
            LOGGER.info("Caching Fibonacci number = {} at index = {}. correlationId={}", fib, index, correlationId);
            cache.putIfAbsent(index, fib);
            a = b;
            b = fib;
        }
        maxCalculatedIndex.set(n);
        LOGGER.info("New max calculated index = {}. correlationId={}", n, correlationId);
    }
}
