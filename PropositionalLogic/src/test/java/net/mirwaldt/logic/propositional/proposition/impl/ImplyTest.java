package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static java.util.Arrays.asList;
import static net.mirwaldt.logic.propositional.proposition.api.Proposition.*;
import static org.junit.jupiter.api.Assertions.*;

public class ImplyTest {
    private final Proposition A = variable("A");
    private final Proposition B = variable("B");
    
    private final Proposition A_IMPLY_B = A.imply(B);

    @Test
    void test_expression() {
        assertEquals("A → B", A_IMPLY_B.toExpression());
    }

    @Test
    void test_evaluate() {
        assertTrue(A_IMPLY_B.evaluate(PropositionInterpretation.of("A", true, "B", true)));
        assertFalse(A_IMPLY_B.evaluate(PropositionInterpretation.of("A", true, "B", false)));
        assertTrue(A_IMPLY_B.evaluate(PropositionInterpretation.of("A", false, "B", true)));
        assertTrue(A_IMPLY_B.evaluate(PropositionInterpretation.of("A", false, "B", false)));
    }
    
    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A", "B"), A_IMPLY_B.findVariableNames());
    }
    
    @Test
    void testGetPropositions() {
        assertEquals(asList(A, B), ((ListProposition) A_IMPLY_B).getPropositions());
    }
}
