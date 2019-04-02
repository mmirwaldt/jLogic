package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static net.mirwaldt.logic.propositional.impl.Interpretations.forMap;
import static net.mirwaldt.logic.propositional.impl.Propositions.negate;
import static net.mirwaldt.logic.propositional.impl.Propositions.or;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;
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
                forMap(Map.of("A", fromBit(0), "B", fromBit(0), "C", fromBit(0), "D", fromBit(0)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(0), "C", fromBit(0), "D", fromBit(1)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(0), "C", fromBit(1), "D", fromBit(0)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(0), "C", fromBit(1), "D", fromBit(1)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(1), "C", fromBit(0), "D", fromBit(0)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(1), "C", fromBit(0), "D", fromBit(1)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(1), "C", fromBit(1), "D", fromBit(0)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(0), "B", fromBit(1), "C", fromBit(1), "D", fromBit(1)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(0), "C", fromBit(0), "D", fromBit(0)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(0), "C", fromBit(0), "D", fromBit(1)))));
        assertFalse(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(0), "C", fromBit(1), "D", fromBit(0)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(0), "C", fromBit(1), "D", fromBit(1)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(1), "C", fromBit(0), "D", fromBit(0)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(1), "C", fromBit(0), "D", fromBit(1)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(1), "C", fromBit(1), "D", fromBit(0)))));
        assertTrue(NOT_A_OR_B_OR_NOT_C_OR_D.evaluate(
                forMap(Map.of("A", fromBit(1), "B", fromBit(1), "C", fromBit(1), "D", fromBit(1)))));
    }
}
