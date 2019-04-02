package net.mirwaldt.logic.propositional.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static net.mirwaldt.logic.propositional.impl.Propositions.*;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class PropositionUtilsTest {
    @Test
    public void test_toFinalExpression() {
        assertEquals("1", PropositionUtils.toFinalExpression(TRUE));
        assertEquals("A", PropositionUtils.toFinalExpression(variable("A")));
        assertEquals("¬A", PropositionUtils.toFinalExpression(negate(variable("A"))));
        assertEquals("(A ∧ B)", PropositionUtils.toFinalExpression(and(variable("A"), variable("B"))));
        assertEquals("f(A)",
                PropositionUtils.toFinalExpression(
                        function("f", asList(variable("A")),
                                Collections.singletonMap(fromBits(0), fromBit(0)))));
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
    void test_toBits() {
        assertEquals(emptyList(), toBits(asList()));
        assertEquals(singletonList(0), toBits(singletonList(false)));
        assertEquals(asList(0, 1), toBits(asList(false, true)));
    }

    @Test
    void test_fromBit() {
        assertFalse(fromBit(0));
        assertTrue(fromBit(1));
        //noinspection ResultOfMethodCallIgnored
        assertThrows(IllegalArgumentException.class, () -> fromBit(-1));
    }
    
    @Test
    void test_fromBits() {
        assertEquals(emptyList(), fromBits());
        assertEquals(singletonList(true), fromBits(1));
        assertEquals(asList(false, true), fromBits(0, 1));
    }
}
