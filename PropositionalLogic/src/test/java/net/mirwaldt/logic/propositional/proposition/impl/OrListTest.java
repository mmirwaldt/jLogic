package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static java.util.Arrays.asList;
import static net.mirwaldt.logic.propositional.proposition.api.Proposition.*;
import static org.junit.jupiter.api.Assertions.*;

public class OrListTest {
    private final Proposition A = variable("A");
    private final Proposition B = variable("B");
    private final Proposition C = variable("C");
    private final Proposition D = variable("D");

    private final Proposition NOT_A_OR_B_OR_NOT_C_OR_D = not(A).or(B).or(not(C)).or(D);
    private final Proposition NOT_A_OR_B_OR_C_AND_D = not(A).or(B).or(C.and(D));

    @Test
    void test_expression() {
        assertEquals("¬A ∨ B ∨ (C ∧ D)", NOT_A_OR_B_OR_C_AND_D.toExpression());
        assertEquals("¬A ∨ B ∨ ¬C ∨ D", NOT_A_OR_B_OR_NOT_C_OR_D.toExpression());
    }

    @Test
    void test_evaluate() {
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 0, "B", 0, "C", 0, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 0, "B", 0, "C", 0, "D", 1)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 0, "B", 0, "C", 1, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 0, "B", 0, "C", 1, "D", 1)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 0, "B", 1, "C", 0, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 0, "B", 1, "C", 0, "D", 1)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 0, "B", 1, "C", 1, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 0, "B", 1, "C", 1, "D", 1)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 1, "B", 0, "C", 0, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 1, "B", 0, "C", 0, "D", 1)));
        assertFalse(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 1, "B", 0, "C", 1, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 1, "B", 0, "C", 1, "D", 1)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 1, "B", 1, "C", 0, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 1, "B", 1, "C", 0, "D", 1)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 1, "B", 1, "C", 1, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                PropositionInterpretation.of("A", 1, "B", 1, "C", 1, "D", 1)));
    }
    
    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A", "B", "C", "D"), NOT_A_OR_B_OR_NOT_C_OR_D.findVariableNames());
    }
    
    @Test
    void testGetPropositions() {
        assertEquals(asList(not(A), B, not(C), D), ((ListProposition) NOT_A_OR_B_OR_NOT_C_OR_D).getPropositions());
    }
}
