package net.mirwaldt.logic.propositional.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterpretationTest {
    @Test
    void test() {
        assertEquals(0, Interpretation.of("A", 0).getAsBit("A"));
        assertEquals(0, Interpretation.of("B", 0).getAsBit("B"));
    }
}
