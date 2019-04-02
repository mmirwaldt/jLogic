package net.mirwaldt.logic.propositional.api;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static net.mirwaldt.logic.propositional.impl.Interpretations.forMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterpretationTest {
    @Test
    void test() {
        assertEquals(0, forMap(Collections.singletonMap("A", false)).getAsBit("A"));
        assertEquals(0, forMap(Collections.singletonMap("B", false)).getAsBit("B"));
    }
}
