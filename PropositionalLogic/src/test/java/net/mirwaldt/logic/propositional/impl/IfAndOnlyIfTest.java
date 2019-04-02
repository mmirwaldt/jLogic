package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static net.mirwaldt.logic.propositional.impl.Interpretations.forMap;
import static net.mirwaldt.logic.propositional.impl.Propositions.and;
import static net.mirwaldt.logic.propositional.impl.Propositions.ifAndOnlyIf;
import static org.junit.jupiter.api.Assertions.*;

public class IfAndOnlyIfTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");
    
    private final Proposition A_IF_AND_ONLY_IF_B = ifAndOnlyIf(A, B);

    @Test
    void test_expression() {
        assertEquals("A ↔ B", A_IF_AND_ONLY_IF_B.toExpression());
    }

    @Test
    void test_evaluate() {
        assertTrue(A_IF_AND_ONLY_IF_B.evaluate(forMap(Map.of("A", true, "B", true))));
        assertFalse(A_IF_AND_ONLY_IF_B.evaluate(forMap(Map.of("A", true, "B", false))));
        assertFalse(A_IF_AND_ONLY_IF_B.evaluate(forMap(Map.of("A", false, "B", true))));
        assertTrue(A_IF_AND_ONLY_IF_B.evaluate(forMap(Map.of("A", false, "B", false))));
    }
}
