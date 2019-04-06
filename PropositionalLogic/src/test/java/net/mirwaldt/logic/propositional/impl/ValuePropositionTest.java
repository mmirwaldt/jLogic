package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static net.mirwaldt.logic.propositional.impl.Propositions.FALSE;
import static net.mirwaldt.logic.propositional.impl.Propositions.TRUE;
import static org.junit.jupiter.api.Assertions.*;

public class ValuePropositionTest {
    @Test
    void test_TRUE() {
        assertEquals(String.valueOf(1), TRUE.toExpression());
        assertTrue(TRUE.evaluate(Interpretation.of("A", 1)));
        assertEquals(Collections.emptySet(), TRUE.findVariableNames());
        assertEquals(FALSE, TRUE.negate());
    }

    @Test
    void test_FALSE() {
        assertEquals(String.valueOf(0), FALSE.toExpression());
        assertFalse(FALSE.evaluate(Interpretation.of("A", 1)));
        assertEquals(Collections.emptySet(), FALSE.findVariableNames());
        assertEquals(TRUE, FALSE.negate());
    }
}
