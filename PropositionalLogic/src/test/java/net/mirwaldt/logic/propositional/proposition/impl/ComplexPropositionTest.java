package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Set;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.*;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;
import static org.junit.jupiter.api.Assertions.*;

public class ComplexPropositionTest {
    private final Proposition A = variable("A");
    private final Proposition B = variable("B");
    private final Proposition C = variable("C");
    private final Proposition D = variable("D");
    
    private final Proposition complex = (A.or(B)).nand(not(C).imply(D));
    
    @Test
    void test_toExpression() {
        assertEquals("¬((A ∨ B) ∧ (¬C → D))", complex.toExpression());
    }

    @Test
    void test_evaluate_missing() {
        assertThrows(NoSuchElementException.class, ()-> complex.evaluate(PropositionInterpretation.of("A", fromBit(0))));
    }   
    
    @Test
    void test_evaluate() {
        assertTrue(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(0), "B", fromBit(0), "C", fromBit(0), "D", fromBit(0))));
        assertTrue(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(0), "B", fromBit(0), "C", fromBit(0), "D", fromBit(1))));
        assertTrue(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(0), "B", fromBit(0), "C", fromBit(1), "D", fromBit(0))));
        assertTrue(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(0), "B", fromBit(0), "C", fromBit(1), "D", fromBit(1))));
        assertTrue(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(0), "B", fromBit(1), "C", fromBit(0), "D", fromBit(0))));
        assertFalse(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(0), "B", fromBit(1), "C", fromBit(0), "D", fromBit(1))));
        assertFalse(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(0), "B", fromBit(1), "C", fromBit(1), "D", fromBit(0))));
        assertFalse(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(0), "B", fromBit(1), "C", fromBit(1), "D", fromBit(1))));
        assertTrue(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(1), "B", fromBit(0), "C", fromBit(0), "D", fromBit(0))));
        assertFalse(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(1), "B", fromBit(0), "C", fromBit(0), "D", fromBit(1))));
        assertFalse(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(1), "B", fromBit(0), "C", fromBit(1), "D", fromBit(0))));
        assertFalse(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(1), "B", fromBit(0), "C", fromBit(1), "D", fromBit(1))));
        assertTrue(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(1), "B", fromBit(1), "C", fromBit(0), "D", fromBit(0))));
        assertFalse(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(1), "B", fromBit(1), "C", fromBit(0), "D", fromBit(1))));
        assertFalse(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(1), "B", fromBit(1), "C", fromBit(1), "D", fromBit(0))));
        assertFalse(complex.evaluate(
                PropositionInterpretation.of("A", fromBit(1), "B", fromBit(1), "C", fromBit(1), "D", fromBit(1))));
    }
    
    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("A", "B", "C", "D"), complex.findVariableNames());
    }
}
