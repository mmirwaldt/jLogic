package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class VariablePropositionTest {
    private final Proposition A = Propositions.variable("A");

    @Test
    void test_nullForbidden() {
        assertThrows(NullPointerException.class, () -> Propositions.variable(null));
    }

    @Test
    void test_toExpression() {
        assertEquals("A", A.toExpression());
    }

    @Test
    void test_evaluateWithoutValue() {
        assertThrows(NoSuchElementException.class, () -> A.evaluate(Interpretation.of("B", true)));
    }

    @Test
    void test_evaluateWithValueFalse() {
        assertFalse(A.evaluate(Interpretation.of("A", false)));
    }

    @Test
    void test_evaluateWithValueTrue() {
        assertTrue(A.evaluate(Interpretation.of("A", true)));
    }
    
    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A"), A.findVariableNames());
    }
}
