package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static net.mirwaldt.logic.propositional.impl.Interpretations.forMap;
import static net.mirwaldt.logic.propositional.impl.Propositions.and;
import static org.junit.jupiter.api.Assertions.*;

public class AndTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");
    
    private final Proposition A_AND_B = and(A, B);

    @Test
    void test_expression() {
        assertEquals("A ∧ B", A_AND_B.toExpression());
    }

    @Test
    void test_evaluate() {
        assertTrue(A_AND_B.evaluate(forMap(Map.of("A", true, "B", true))));
        assertFalse(A_AND_B.evaluate(forMap(Map.of("A", true, "B", false))));
        assertFalse(A_AND_B.evaluate(forMap(Map.of("A", false, "B", true))));
        assertFalse(A_AND_B.evaluate(forMap(Map.of("A", false, "B", false))));
    }
}
