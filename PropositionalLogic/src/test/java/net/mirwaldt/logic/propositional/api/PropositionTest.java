package net.mirwaldt.logic.propositional.api;

import net.mirwaldt.logic.propositional.impl.Propositions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropositionTest {
    private final Proposition A = Propositions.variable("A");    
    
    @Test
    void test_evaluateAsBit() {
        assertEquals(0, A.evaluateAsBit(Interpretation.of("A", 0)));
        assertEquals(1, A.evaluateAsBit(Interpretation.of("A", 1)));
    }
}
