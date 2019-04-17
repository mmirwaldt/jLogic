package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static net.mirwaldt.logic.propositional.api.Proposition.*;
import static org.junit.jupiter.api.Assertions.*;

public class OrTest {
    private final Proposition A = variable("A");
    private final Proposition B = variable("B");
    
    private final Proposition A_OR_B = A.or(B);

    @Test
    void test_expression() {
        assertEquals("A ∨ B", A_OR_B.toExpression());
    }

    @Test
    void test_evaluate() {
        assertTrue(A_OR_B.evaluate(Interpretation.of("A", true, "B", true)));
        assertTrue(A_OR_B.evaluate(Interpretation.of("A", true, "B", false)));
        assertTrue(A_OR_B.evaluate(Interpretation.of("A", false, "B", true)));
        assertFalse(A_OR_B.evaluate(Interpretation.of("A", false, "B", false)));
    }
    
    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A", "B"), A_OR_B.findVariableNames());
    }
}
