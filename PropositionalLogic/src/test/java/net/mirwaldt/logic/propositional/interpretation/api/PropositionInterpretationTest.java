package net.mirwaldt.logic.propositional.interpretation.api;

import org.junit.jupiter.api.Test;

import static net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation.pair;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropositionInterpretationTest {
    @Test
    void test_nonPairFactory() {
        assertEquals(0, PropositionInterpretation.of("A", 0).getAsBit("A"));
        assertEquals(1, PropositionInterpretation.of("B", 1).getAsBit("B"));
        assertEquals("0 1", 
                PropositionInterpretation.of("A", 0, "B", 1).asBitsWhitespaceSeparated());
    }

    @Test
    void test_pairFactory() {
        PropositionInterpretation interpretation = PropositionInterpretation.ofPairs(
                pair("A", 0), pair("B", 1));
        assertEquals(0,interpretation.getAsBit("A"));
        assertEquals(1, interpretation.getAsBit("B"));
        assertEquals("0 1", interpretation.asBitsWhitespaceSeparated()); 
        assertThrows(IllegalArgumentException.class, ()-> PropositionInterpretation.ofPairs(
                pair("A", 0), new PropositionInterpretation.Pair[64]));
    }
}
