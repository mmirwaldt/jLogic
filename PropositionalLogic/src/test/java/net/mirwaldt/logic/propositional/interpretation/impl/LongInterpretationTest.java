package net.mirwaldt.logic.propositional.interpretation.impl;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

public class LongInterpretationTest {
    @Test
    void test_emptyListOfVariableNames() {
        assertThrows(IllegalArgumentException.class, () -> new LongInterpretation(Collections.emptyList(), 0));
    }
    
    @Test
    void test_tooManyVariableNames() {
        final List<String> variableNames = IntStream.rangeClosed(1, 64).mapToObj(String::valueOf).collect(toList());
        assertThrows(IllegalArgumentException.class, () -> new LongInterpretation(variableNames, 0));
    }
    
    @Test
    void test_oneVariableName() {
        new LongInterpretation(Collections.singletonList("A"), 0);
    }

    @Test
    void test_maxNumberOfVariableVariableNames() {
        final List<String> variableNames = IntStream.rangeClosed(1, 63).mapToObj(String::valueOf).collect(toList());
        new LongInterpretation(variableNames, 0);
    }
    
    @Test
    void test_oneVariable() {
        Interpretation interpretation = new LongInterpretation(Collections.singletonList("A"), 1);
        assertEquals(Collections.singletonList("A"), interpretation.getVariableNames());
        assertTrue(interpretation.get("A"));
        assertThrows(NoSuchElementException.class, () -> interpretation.get("B"));
    }
    
    @Test
    void test_twoVariables() {
        Interpretation interpretation = new LongInterpretation(List.of("A", "B"), 2);
        assertEquals(List.of("A", "B"), interpretation.getVariableNames());
        assertFalse(interpretation.get("A"));
        assertTrue(interpretation.get("B"));
        assertThrows(NoSuchElementException.class, () -> interpretation.get("C"));
    }

    @Test
    void test_maxNumberOfVariables() {
        final List<String> variableNames = IntStream.rangeClosed(1, 63).mapToObj(String::valueOf).collect(toList());
        Interpretation interpretation = new LongInterpretation(variableNames, 0x4000_0000_0000_0001L);
        assertEquals(variableNames, interpretation.getVariableNames());
        assertTrue(interpretation.get("1"));
        assertFalse(interpretation.get("2"));
        // skip other bits between
        assertTrue(interpretation.get("63"));
        assertThrows(NoSuchElementException.class, () -> interpretation.get("64"));
    }
}
