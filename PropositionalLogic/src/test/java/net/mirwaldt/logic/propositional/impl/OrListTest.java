package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static net.mirwaldt.logic.propositional.impl.Propositions.negate;
import static net.mirwaldt.logic.propositional.impl.Propositions.or;
import static org.junit.jupiter.api.Assertions.*;

public class OrListTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");
    private final Proposition C = Propositions.variable("C");
    private final Proposition D = Propositions.variable("D");

    private final Proposition NOT_A_OR_B_OR_NOT_C_OR_D = or(negate(A), B, negate(C), D);

    @Test
    void test_expression() {
        assertEquals("¬A ∨ B ∨ ¬C ∨ D", NOT_A_OR_B_OR_NOT_C_OR_D.toExpression());
    }

    @Test
    void test_evaluate() {
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 0, "B", 0, "C", 0, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 0, "B", 0, "C", 0, "D", 1)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 0, "B", 0, "C", 1, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 0, "B", 0, "C", 1, "D", 1)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 0, "B", 1, "C", 0, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 0, "B", 1, "C", 0, "D", 1)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 0, "B", 1, "C", 1, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 0, "B", 1, "C", 1, "D", 1)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 1, "B", 0, "C", 0, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 1, "B", 0, "C", 0, "D", 1)));
        assertFalse(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 1, "B", 0, "C", 1, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 1, "B", 0, "C", 1, "D", 1)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 1, "B", 1, "C", 0, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 1, "B", 1, "C", 0, "D", 1)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 1, "B", 1, "C", 1, "D", 0)));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                Interpretation.of("A", 1, "B", 1, "C", 1, "D", 1)));
    }
    
    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A", "B", "C", "D"), NOT_A_OR_B_OR_NOT_C_OR_D.findVariableNames());
    }
}
