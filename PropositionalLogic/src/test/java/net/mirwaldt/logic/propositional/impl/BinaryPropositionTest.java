package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static net.mirwaldt.logic.propositional.impl.Propositions.negate;
import static net.mirwaldt.logic.propositional.impl.Propositions.or;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryPropositionTest {
    private final Proposition A = Propositions.variable("A");
    private final Proposition B = Propositions.variable("B");
    
    private final Proposition inverseImplication = Propositions.binary(A, negate(B),
            (left, right) -> left | right, "%s ∨ %s", 
            (left, right) -> or(left, right).negate());
    private final Proposition negatedInverseImplication = inverseImplication.negate();

    @Test
    void test_wrongExpression() {
        assertThrows(IllegalArgumentException.class,
                () -> Propositions.binary(A, negate(B),
                        (left, right) -> left | right, "noPlaceHolder",
                        (left, right) -> or(left, right).negate()));
        assertThrows(IllegalArgumentException.class,
                () -> Propositions.binary(A, negate(B),
                        (left, right) -> left | right, "%s",
                        (left, right) -> or(left, right).negate()));
        assertThrows(IllegalArgumentException.class,
                () -> Propositions.binary(A, negate(B),
                        (left, right) -> left | right, "%s %s %s",
                        (left, right) -> or(left, right).negate()));
    }
    
    @Test
    void test_expression() {
        assertEquals("A ∨ ¬B", inverseImplication.toExpression());
        assertEquals("¬A ∧ ¬¬B", negatedInverseImplication.toExpression());
    }
    
    @Test
    void test_evaluate() {
        assertTrue(inverseImplication.evaluate(Interpretation.of("A", true, "B", true)));
        assertTrue(inverseImplication.evaluate(Interpretation.of("A", true, "B", false)));
        assertFalse(inverseImplication.evaluate(Interpretation.of("A", false, "B", true)));
        assertTrue(inverseImplication.evaluate(Interpretation.of("A", false, "B", false)));

        assertFalse(negatedInverseImplication.evaluate(Interpretation.of("A", true, "B", true)));
        assertFalse(negatedInverseImplication.evaluate(Interpretation.of("A", true, "B", false)));
        assertTrue(negatedInverseImplication.evaluate(Interpretation.of("A", false, "B", true)));
        assertFalse(negatedInverseImplication.evaluate(Interpretation.of("A", false, "B", false)));
    }

    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A", "B"), inverseImplication.findVariableNames());
        assertEquals(Set.of("A", "B"), negatedInverseImplication.findVariableNames());
    }
}
