package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class NegateTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition NEGATED_A = Propositions.negate(A);
    
    @Test
    void test_toExpression() {
        assertEquals("¬A", NEGATED_A.toExpression());
    }

    @Test
    void test_evaluateWithValueFalse() {
        assertTrue(NEGATED_A.evaluate(Collections.singletonMap("A", false)));
    }

    @Test
    void test_evaluateWithValueTrue() {
        assertFalse(NEGATED_A.evaluate(Collections.singletonMap("A", true)));
    }     
}
