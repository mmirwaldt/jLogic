package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static net.mirwaldt.logic.propositional.impl.Propositions.iff;
import static org.junit.jupiter.api.Assertions.*;

public class IffTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");
    
    private final Proposition A_IFF_B = iff(A, B);

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
