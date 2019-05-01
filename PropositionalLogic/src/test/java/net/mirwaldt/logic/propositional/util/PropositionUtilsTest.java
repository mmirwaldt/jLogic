package net.mirwaldt.logic.propositional.util;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static net.mirwaldt.logic.propositional.proposition.api.Proposition.*;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class PropositionUtilsTest {
    @Test
    public void test_toFinalExpression() {
        assertEquals("1", PropositionUtils.toFinalExpression(TRUE));
        assertEquals("A", PropositionUtils.toFinalExpression(variable("A")));
        assertEquals("¬A", PropositionUtils.toFinalExpression(not(variable("A"))));
        assertEquals("(A ∧ B)", PropositionUtils.toFinalExpression(and(variable("A"), variable("B"))));
        assertEquals("f(A)",
                PropositionUtils.toFinalExpression(
                        function("f", asList("A"), asList(PropositionInterpretation.of("A", 1)), 0)));
        assertEquals("(A ∧ B ∧ C)",
                PropositionUtils.toFinalExpression(
                        and(variable("A"), variable("B"), variable("C"))));
    }

    @Test
    void test_toBit() {
        assertEquals(0, toBit(false));
        assertEquals(1, toBit(true));
    }

    @Test
    void test_fromBit() {
        assertFalse(fromBit(0));
        assertTrue(fromBit(1));
        //noinspection ResultOfMethodCallIgnored
        assertThrows(IllegalArgumentException.class, () -> fromBit(-1));
    }
}
