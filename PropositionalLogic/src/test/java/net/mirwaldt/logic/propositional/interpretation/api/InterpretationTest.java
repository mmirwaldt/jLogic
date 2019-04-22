package net.mirwaldt.logic.propositional.interpretation.api;

import org.junit.jupiter.api.Test;

import static net.mirwaldt.logic.propositional.interpretation.api.Interpretation.pair;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InterpretationTest {
    @Test
    void test_nonPairFactory() {
        assertEquals(0, Interpretation.of("A", 0).getAsBit("A"));
        assertEquals(1, Interpretation.of("B", 1).getAsBit("B"));
        assertEquals("0 1", 
                Interpretation.of("A", 0, "B", 1).asBitsWhitespaceSeparated());
    }

    @Test
    void test_pairFactory() {
        Interpretation interpretation = Interpretation.ofPairs(
                pair("A", 0), pair("B", 1));
        assertEquals(0,interpretation.getAsBit("A"));
        assertEquals(1, interpretation.getAsBit("B"));
        assertEquals("0 1", interpretation.asBitsWhitespaceSeparated()); 
        assertThrows(IllegalArgumentException.class, ()->Interpretation.ofPairs(
                pair("A", 0), new Interpretation.Pair[63]));
    }
}
