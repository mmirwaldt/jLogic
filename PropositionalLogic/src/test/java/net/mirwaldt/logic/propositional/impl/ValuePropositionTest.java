package net.mirwaldt.logic.propositional.impl;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static net.mirwaldt.logic.propositional.impl.Interpretations.forMap;
import static org.junit.jupiter.api.Assertions.*;

public class ValuePropositionTest {
    @Test
    void test_TRUE() {
        assertEquals(String.valueOf(1), Propositions.TRUE.toExpression());
        assertTrue(Propositions.TRUE.evaluate(forMap(Collections.emptyMap())));
    }

    @Test
    void test_FALSE() {
        assertEquals(String.valueOf(0), Propositions.FALSE.toExpression());
        assertFalse(Propositions.FALSE.evaluate(forMap(Collections.emptyMap())));
    }
}
