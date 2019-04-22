package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.not;
import static net.mirwaldt.logic.propositional.proposition.api.Proposition.variable;
import static org.junit.jupiter.api.Assertions.*;

public class AndListTest {
    private final Proposition A = variable("A");
    private final Proposition B = variable("B");
    private final Proposition C = variable("C");
    private final Proposition D = variable("D");

    private final Proposition A_AND_B_AND_NOT_C_AND_D = A.and(B).and(not(C)).and(D);
    private final Proposition A_AND_B_AND_NOT_C_OR_D = A.and(B).and(not(C).or(D));
    
    @Test
    void test_expression() {
        assertEquals("A ∧ B ∧ (¬C ∨ D)", A_AND_B_AND_NOT_C_OR_D.toExpression());
        assertEquals("A ∧ B ∧ ¬C ∧ D", A_AND_B_AND_NOT_C_AND_D.toExpression());
    }

    @Test
    void test_evaluate() {
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 0, "B", 0, "C", 0, "D", 0)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 0, "B", 0, "C", 0, "D", 1)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 0, "B", 0, "C", 1, "D", 0)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 0, "B", 0, "C", 1, "D", 1)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 0, "B", 1, "C", 0, "D", 0)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 0, "B", 1, "C", 0, "D", 1)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 0, "B", 1, "C", 1, "D", 0)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 0, "B", 1, "C", 1, "D", 1)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 1, "B", 0, "C", 0, "D", 0)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 1, "B", 0, "C", 0, "D", 1)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 1, "B", 0, "C", 1, "D", 0)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 1, "B", 0, "C", 1, "D", 1)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 1, "B", 1, "C", 0, "D", 0)));
        assertTrue(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 1, "B", 1, "C", 0, "D", 1)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 1, "B", 1, "C", 1, "D", 0)));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                Interpretation.of("A", 1, "B", 1, "C", 1, "D", 1)));
    }
    
    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A", "B", "C", "D"), A_AND_B_AND_NOT_C_AND_D.findVariableNames());
    }
}
