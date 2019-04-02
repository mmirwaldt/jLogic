package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static net.mirwaldt.logic.propositional.impl.Propositions.nand;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NandTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");

    private final Proposition A_NAND_B = nand(A, B);

    @Test
    void test_expression() {
        assertEquals("¬(A ∧ B)", A_NAND_B.toExpression());
    }

    @Test
    void test_evaluate() {
        assertFalse(A_NAND_B.evaluate(Map.of("A", true, "B", true)));
        assertTrue(A_NAND_B.evaluate(Map.of("A", true, "B", false)));
        assertTrue(A_NAND_B.evaluate(Map.of("A", false, "B", true)));
        assertTrue(A_NAND_B.evaluate(Map.of("A", false, "B", false)));
    }
}
