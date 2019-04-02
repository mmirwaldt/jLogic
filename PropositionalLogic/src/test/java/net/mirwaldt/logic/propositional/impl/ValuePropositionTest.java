package net.mirwaldt.logic.propositional.impl;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static net.mirwaldt.logic.propositional.impl.Interpretations.forMap;
import static net.mirwaldt.logic.propositional.impl.Propositions.FALSE;
import static net.mirwaldt.logic.propositional.impl.Propositions.TRUE;
import static org.junit.jupiter.api.Assertions.*;

public class ValuePropositionTest {
    @Test
    void test_TRUE() {
        assertEquals(String.valueOf(1), TRUE.toExpression());
        assertTrue(TRUE.evaluate(forMap(Collections.emptyMap())));
        assertEquals(Collections.emptySet(), TRUE.findVariableNames());
    }

    @Test
    void test_FALSE() {
        assertEquals(String.valueOf(0), FALSE.toExpression());
        assertFalse(FALSE.evaluate(forMap(Collections.emptyMap())));
        assertEquals(Collections.emptySet(), FALSE.findVariableNames());
    }
}
