package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.NoSuchElementException;

import static net.mirwaldt.logic.propositional.impl.Interpretations.forMap;
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
    void test_evaluateWithoutValue_emptyMap() {
        assertThrows(NoSuchElementException.class, () -> A.evaluate(forMap(Collections.emptyMap())));
    }
    
    @Test
    void test_evaluateWithoutValue_nonEmptyMap() {
        assertThrows(NoSuchElementException.class, () -> A.evaluate(forMap(Collections.singletonMap("B", true))));
    }

    @Test
    void test_evaluateWithValueFalse() {
        assertFalse(A.evaluate(forMap(Collections.singletonMap("A", false))));
    }

    @Test
    void test_evaluateWithValueTrue() {
        assertTrue(A.evaluate(forMap(Collections.singletonMap("A", true))));
    }
}
