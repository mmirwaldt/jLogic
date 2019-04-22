package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.*;
import static org.junit.jupiter.api.Assertions.*;

public class IffTest {
    private final Proposition A = variable("A");
    private final Proposition B = variable("B");
    
    private final Proposition A_IFF_B = A.iff(B);

    @Test
    void test_expression() {
        assertEquals("A ↔ B", A_IFF_B.toExpression());
    }

    @Test
    void test_evaluate() {
        assertTrue(A_IFF_B.evaluate(Interpretation.of("A", true, "B", true)));
        assertFalse(A_IFF_B.evaluate(Interpretation.of("A", true, "B", false)));
        assertFalse(A_IFF_B.evaluate(Interpretation.of("A", false, "B", true)));
        assertTrue(A_IFF_B.evaluate(Interpretation.of("A", false, "B", false)));
    }
    
    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A", "B"), A_IFF_B.findVariableNames());
    }
}
