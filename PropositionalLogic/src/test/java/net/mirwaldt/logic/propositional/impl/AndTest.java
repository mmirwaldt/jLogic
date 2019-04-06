package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static net.mirwaldt.logic.propositional.impl.Propositions.and;
import static org.junit.jupiter.api.Assertions.*;

public class AndTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");
    
    private final Proposition A_AND_B = and(A, B);
    private final Proposition NEGATED_A_AND_B = A_AND_B.negate();

    @Test
    void test_expression() {
        assertEquals("A ∧ B", A_AND_B.toExpression());
        assertEquals("¬A ∨ ¬B", NEGATED_A_AND_B.toExpression());
    }

    @Test
    void test_evaluate() {
        assertTrue(A_AND_B.evaluate(Interpretation.of("A", true, "B", true)));
        assertFalse(A_AND_B.evaluate(Interpretation.of("A", true, "B", false)));
        assertFalse(A_AND_B.evaluate(Interpretation.of("A", false, "B", true)));
        assertFalse(A_AND_B.evaluate(Interpretation.of("A", false, "B", false)));

        assertFalse(NEGATED_A_AND_B.evaluate(Interpretation.of("A", true, "B", true)));
        assertTrue(NEGATED_A_AND_B.evaluate(Interpretation.of("A", true, "B", false)));
        assertTrue(NEGATED_A_AND_B.evaluate(Interpretation.of("A", false, "B", true)));
        assertTrue(NEGATED_A_AND_B.evaluate(Interpretation.of("A", false, "B", false)));
    }

    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A", "B"), A_AND_B.findVariableNames());
        assertEquals(Set.of("A", "B"), NEGATED_A_AND_B.findVariableNames());
    }
}
