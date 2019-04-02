package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static net.mirwaldt.logic.propositional.impl.Interpretations.forMap;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryPropositionTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");
    
    private final Proposition inverseImplication = Propositions.binary(A, B,
            (left, right) -> left | !right, "%s ∨ ¬%s");

    @Test
    void test_expression() {
        assertEquals("A ∨ ¬B", inverseImplication.toExpression());
    }
    
    @Test
    void test_evaluate() {
        assertTrue(inverseImplication.evaluate(forMap(Map.of("A", true, "B", true))));
        assertTrue(inverseImplication.evaluate(forMap(Map.of("A", true, "B", false))));
        assertFalse(inverseImplication.evaluate(forMap(Map.of("A", false, "B", true))));
        assertTrue(inverseImplication.evaluate(forMap(Map.of("A", false, "B", false))));
    }
}
