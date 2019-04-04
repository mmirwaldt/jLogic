package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

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
        assertTrue(inverseImplication.evaluate(Interpretation.of("A", true, "B", true)));
        assertTrue(inverseImplication.evaluate(Interpretation.of("A", true, "B", false)));
        assertFalse(inverseImplication.evaluate(Interpretation.of("A", false, "B", true)));
        assertTrue(inverseImplication.evaluate(Interpretation.of("A", false, "B", false)));
    }

    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A", "B"), inverseImplication.findVariableNames());
    }
}
