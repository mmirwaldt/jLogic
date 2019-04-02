package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static net.mirwaldt.logic.propositional.impl.Interpretations.forMap;
import static net.mirwaldt.logic.propositional.impl.Propositions.and;
import static net.mirwaldt.logic.propositional.impl.Propositions.negate;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;
import static org.junit.jupiter.api.Assertions.*;

public class AndListTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");
    private final Proposition C = Propositions.variable("C");
    private final Proposition D = Propositions.variable("D");

    private final Proposition A_AND_B_AND_NOT_C_AND_D = and(A, B, negate(C), D);
    
    @Test
    void test_expression() {
        assertEquals("A ∧ B ∧ ¬C ∧ D", A_AND_B_AND_NOT_C_AND_D.toExpression());
    }

    @Test
    void test_evaluate() {
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(0), "C", fromBit(0), "D", fromBit(0)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(0), "C", fromBit(0), "D", fromBit(1)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(0), "C", fromBit(1), "D", fromBit(0)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(0), "C", fromBit(1), "D", fromBit(1)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(1), "C", fromBit(0), "D", fromBit(0)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(1), "C", fromBit(0), "D", fromBit(1)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(1), "C", fromBit(1), "D", fromBit(0)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(1), "C", fromBit(1), "D", fromBit(1)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(0), "C", fromBit(0), "D", fromBit(0)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(0), "C", fromBit(0), "D", fromBit(1)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(0), "C", fromBit(1), "D", fromBit(0)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(0), "C", fromBit(1), "D", fromBit(1)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(1), "C", fromBit(0), "D", fromBit(0)))));
        assertTrue(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(1), "C", fromBit(0), "D", fromBit(1)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(1), "C", fromBit(1), "D", fromBit(0)))));
        assertFalse(A_AND_B_AND_NOT_C_AND_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(1), "C", fromBit(1), "D", fromBit(1)))));
    }
}
