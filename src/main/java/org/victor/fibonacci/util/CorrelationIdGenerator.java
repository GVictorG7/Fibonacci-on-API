package org.victor.fibonacci.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Generator of unique ids for logging purposes request identification
 */
@Component
public class CorrelationIdGenerator {

    /**
     * Generates an unique id
     *
     * @return an unique id in String format
     */
    public synchronized String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
