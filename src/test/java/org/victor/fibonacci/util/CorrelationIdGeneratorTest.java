package org.victor.fibonacci.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CorrelationIdGeneratorTest {
    private CorrelationIdGenerator classUnderTest;

    @BeforeEach
    void setUpClass() {
        classUnderTest = new CorrelationIdGenerator();
    }

    @Test
    void testGetCorrelationId() {
        // WHEN
        String correlationId = classUnderTest.generateCorrelationId();

        // THEN
        assertNotNull(correlationId);
    }
}
