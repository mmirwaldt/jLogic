package net.mirwaldt.logic.propositional.api;

import org.junit.jupiter.api.Test;

import static net.mirwaldt.logic.propositional.api.Proposition.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropositionTest {
    private final Proposition A = Proposition.variable("A");
    private final Proposition NEGATED_A = A.negate();

    @Test
    void test_evaluateAsBit() {
        assertEquals(0, A.evaluateAsBit(Interpretation.of("A", 0)));
        assertEquals(1, A.evaluateAsBit(Interpretation.of("A", 1)));
    }


    @Test
    void test_negate() {
        assertEquals(1, NEGATED_A.evaluateAsBit(Interpretation.of("A", 0)));
        assertEquals(0, NEGATED_A.evaluateAsBit(Interpretation.of("A", 1)));
    }
    
    @Test
    void test_value() {
        assertEquals(FALSE, value(false));
        assertEquals(TRUE, value(true));
        assertEquals(FALSE, value(0));
        assertEquals(TRUE, value(1));
    }
}
