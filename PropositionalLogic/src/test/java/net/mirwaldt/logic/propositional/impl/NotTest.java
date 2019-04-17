package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static net.mirwaldt.logic.propositional.api.Proposition.*;
import static org.junit.jupiter.api.Assertions.*;

public class NotTest {
    private final Proposition A = variable("A");
    private final Proposition B = variable("B");
    private final Proposition NOT_A = not(A);
    
    @Test
    void test_toExpression() {
        assertEquals("¬A", NOT_A.toExpression());
    }

    @Test
    void test_evaluateWithValueFalse() {
        assertTrue(NOT_A.evaluate(Interpretation.of("A", false)));
    }

    @Test
    void test_evaluateWithValueTrue() {
        assertFalse(NOT_A.evaluate(Interpretation.of("A", true)));
    }

    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A"), NOT_A.findVariableNames());
    }


    @Test
    void testEquals() {
        assertNotEquals(NOT_A, null);
        assertEquals(NOT_A, NOT_A);
        assertEquals(NOT_A, not(A));
        assertNotEquals(NOT_A, A);
        assertNotEquals(NOT_A, A.and(B));
        assertNotEquals(NOT_A, not(B));
        assertNotEquals(NOT_A, TRUE);
        assertNotEquals(NOT_A, FALSE);
    }

    @Test
    void testHashCode() {
        assertNotEquals(NOT_A.hashCode(), 0);
        assertEquals(NOT_A.hashCode(), NOT_A.hashCode());
        assertEquals(NOT_A.hashCode(), not(A).hashCode());
    }
}
