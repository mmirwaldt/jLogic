package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AndTest {
    private final Proposition A = Proposition.variable("A");
    private final Proposition B = Proposition.variable("B");
    
    private final Proposition A_AND_B = A.and(B);

    @Test
    void test_expression() {
        assertEquals("A ∧ B", A_AND_B.toExpression());
    }

    @Test
    void test_evaluate() {
        assertTrue(A_AND_B.evaluate(PropositionInterpretation.of("A", true, "B", true)));
        assertFalse(A_AND_B.evaluate(PropositionInterpretation.of("A", true, "B", false)));
        assertFalse(A_AND_B.evaluate(PropositionInterpretation.of("A", false, "B", true)));
        assertFalse(A_AND_B.evaluate(PropositionInterpretation.of("A", false, "B", false)));
    }

    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A", "B"), A_AND_B.findVariableNames());
    }
}
