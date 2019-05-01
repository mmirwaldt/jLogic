package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.*;
import static org.junit.jupiter.api.Assertions.*;

public class ValuePropositionTest {
    @Test
    void test_TRUE() {
        assertEquals(String.valueOf(1), TRUE.toExpression());
        assertTrue(TRUE.evaluate(PropositionInterpretation.of("A", 1)));
        assertEquals(Collections.emptySet(), TRUE.findVariableNames());
    }

    @Test
    void test_FALSE() {
        assertEquals(String.valueOf(0), FALSE.toExpression());
        assertFalse(FALSE.evaluate(PropositionInterpretation.of("A", 1)));
        assertEquals(Collections.emptySet(), FALSE.findVariableNames());
    }

    @Test
    void test_equals() {
        assertFalse(TRUE.equals(null));
        assertFalse(TRUE.equals(FALSE));
        assertFalse(TRUE.equals(variable("A")));
        assertTrue(TRUE.equals(TRUE));
    }
    
    @Test
    void test_hashCodeWithEquals() {
        Set<Proposition> propositions = new HashSet<>();
        propositions.add(TRUE);
        propositions.add(variable("A"));

        assertFalse(propositions.contains(FALSE));
        assertTrue(propositions.contains(TRUE));
    }
}
