package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class NegateTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition NEGATED_A = Propositions.negate(A);
    private final Proposition ANOTHER_NEGATED_A = A.negate();
    
    @Test
    void test_toExpression() {
        assertEquals("¬A", NEGATED_A.toExpression());
        assertEquals("¬A", ANOTHER_NEGATED_A.toExpression());
    }

    @Test
    void test_evaluateWithValueFalse() {
        assertTrue(NEGATED_A.evaluate(Interpretation.of("A", false)));
        assertTrue(ANOTHER_NEGATED_A.evaluate(Interpretation.of("A", false)));
    }

    @Test
    void test_evaluateWithValueTrue() {
        assertFalse(NEGATED_A.evaluate(Interpretation.of("A", true)));
        assertFalse(ANOTHER_NEGATED_A.evaluate(Interpretation.of("A", true)));
    }

    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A"), NEGATED_A.findVariableNames());
        assertEquals(Set.of("A"), ANOTHER_NEGATED_A.findVariableNames());
    }
}
