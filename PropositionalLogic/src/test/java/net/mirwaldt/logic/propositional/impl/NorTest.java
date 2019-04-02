package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static net.mirwaldt.logic.propositional.impl.Interpretations.forMap;
import static net.mirwaldt.logic.propositional.impl.Propositions.and;
import static net.mirwaldt.logic.propositional.impl.Propositions.nor;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NorTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");

    private final Proposition A_NOR_B = nor(A, B);

    @Test
    void test_expression() {
        assertEquals("¬(A ∨ B)", A_NOR_B.toExpression());
    }

    @Test
    void test_evaluate() {
        assertFalse(A_NOR_B.evaluate(forMap(Map.of("A", true, "B", true))));
        assertFalse(A_NOR_B.evaluate(forMap(Map.of("A", true, "B", false))));
        assertFalse(A_NOR_B.evaluate(forMap(Map.of("A", false, "B", true))));
        assertTrue(A_NOR_B.evaluate(forMap(Map.of("A", false, "B", false))));
    }
}
