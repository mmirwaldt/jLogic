package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static net.mirwaldt.logic.propositional.impl.Propositions.and;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InBracketsTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");

    private final Proposition A_AND_B = and(A, B);
    private final Proposition A_AND_B_IN_BRACKETS = Propositions.inBrackets(A_AND_B);

    @Test
    void test_expression() {
        assertEquals("(A ∧ B)", A_AND_B_IN_BRACKETS.toExpression());
    }

    @Test
    void test_evaluate() {
        assertTrue(A_AND_B_IN_BRACKETS.evaluate(Map.of("A", true, "B", true)));
        assertFalse(A_AND_B_IN_BRACKETS.evaluate(Map.of("A", true, "B", false)));
        assertFalse(A_AND_B_IN_BRACKETS.evaluate(Map.of("A", false, "B", true)));
        assertFalse(A_AND_B_IN_BRACKETS.evaluate(Map.of("A", false, "B", false)));
    }
}
