package net.mirwaldt.logic.propositional.impl;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ValuePropositionTest {
    @Test
    void test_TRUE() {
        assertEquals(String.valueOf(true), Propositions.TRUE.toExpression());
        assertTrue(Propositions.TRUE.evaluate(Collections.emptyMap()));
    }

    @Test
    void test_FALSE() {
        assertEquals(String.valueOf(false), Propositions.FALSE.toExpression());
        assertFalse(Propositions.FALSE.evaluate(Collections.emptyMap()));
    }
}
