package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static net.mirwaldt.logic.propositional.impl.Interpretations.forMap;
import static net.mirwaldt.logic.propositional.impl.Propositions.or;
import static net.mirwaldt.logic.propositional.impl.Propositions.xor;
import static org.junit.jupiter.api.Assertions.*;

public class XOrTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");
    
    private final Proposition A_XOR_B = xor(A, B);

    @Test
    void test_expression() {
        assertEquals("A ⩒ B", A_XOR_B.toExpression());
    }

    @Test
    void test_evaluate() {
        assertFalse(A_XOR_B.evaluate(forMap(Map.of("A", true, "B", true))));
        assertTrue(A_XOR_B.evaluate(forMap(Map.of("A", true, "B", false))));
        assertTrue(A_XOR_B.evaluate(forMap(Map.of("A", false, "B", true))));
        assertFalse(A_XOR_B.evaluate(forMap(Map.of("A", false, "B", false))));
    }
}
