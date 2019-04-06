package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static net.mirwaldt.logic.propositional.impl.Propositions.function;
import static net.mirwaldt.logic.propositional.util.BitUtils.encode;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionTest {
    private final List<String> parameters = Arrays.asList("B0", "B1", "B2");
    private final List<Interpretation> interpretations =
            List.of(
                    Interpretation.of("B0", 0, "B1", 0, "B2", 0),
                    Interpretation.of("B0", 0, "B1", 0, "B2", 1),
                    Interpretation.of("B0", 0, "B1", 1, "B2", 0),
                    Interpretation.of("B0", 0, "B1", 1, "B2", 1),
                    Interpretation.of("B0", 1, "B1", 0, "B2", 0),
                    Interpretation.of("B0", 1, "B1", 0, "B2", 1),
                    Interpretation.of("B0", 1, "B1", 1, "B2", 0),
                    Interpretation.of("B0", 1, "B1", 1, "B2", 1)
            );

    private final List<Interpretation> incompleteInterpretations =
            List.of(
                    Interpretation.of("B0", 0, "B1", 0, "B2", 0)
            );
    private long resultBits =
            encode(
                    encode(
                            encode(
                                    encode(0, 2, 1)
                                    , 3, 1)
                            , 5, 1)
                    , 7, 1);

    private final Proposition prime = function("prime", parameters, interpretations, resultBits);
    private final Proposition negatedPrime = prime.negate();
    private final Proposition incompletePrime = 
            function("prime", parameters, incompleteInterpretations, 0);
    private final Proposition negatedIncompletePrime = incompletePrime.negate();
    
    @Test
    void test_toExpression() {
        assertEquals("prime(B0,B1,B2)", prime.toExpression());
        assertEquals("¬prime(B0,B1,B2)", negatedPrime.toExpression());
    }

    @Test
    void test_evaluate() {
        assertFalse(prime.evaluate(
                Interpretation.of("B0", fromBit(0), "B1", fromBit(0), "B2", fromBit(0))));
        assertFalse(prime.evaluate(
                Interpretation.of("B0", fromBit(0), "B1", fromBit(0), "B2", fromBit(1))));
        assertTrue(prime.evaluate(
                Interpretation.of("B0", fromBit(0), "B1", fromBit(1), "B2", fromBit(0))));
        assertTrue(prime.evaluate(
                Interpretation.of("B0", fromBit(0), "B1", fromBit(1), "B2", fromBit(1))));
        assertFalse(prime.evaluate(
                Interpretation.of("B0", fromBit(1), "B1", fromBit(0), "B2", fromBit(0))));
        assertTrue(prime.evaluate(
                Interpretation.of("B0", fromBit(1), "B1", fromBit(0), "B2", fromBit(1))));
        assertFalse(prime.evaluate(
                Interpretation.of("B0", fromBit(1), "B1", fromBit(1), "B2", fromBit(0))));
        assertTrue(prime.evaluate(
                Interpretation.of("B0", fromBit(1), "B1", fromBit(1), "B2", fromBit(1))));
        
        assertTrue(negatedPrime.evaluate(
                Interpretation.of("B0", fromBit(0), "B1", fromBit(0), "B2", fromBit(0))));
        assertTrue(negatedPrime.evaluate(
                Interpretation.of("B0", fromBit(0), "B1", fromBit(0), "B2", fromBit(1))));
        assertFalse(negatedPrime.evaluate(
                Interpretation.of("B0", fromBit(0), "B1", fromBit(1), "B2", fromBit(0))));
        assertFalse(negatedPrime.evaluate(
                Interpretation.of("B0", fromBit(0), "B1", fromBit(1), "B2", fromBit(1))));
        assertTrue(negatedPrime.evaluate(
                Interpretation.of("B0", fromBit(1), "B1", fromBit(0), "B2", fromBit(0))));
        assertFalse(negatedPrime.evaluate(
                Interpretation.of("B0", fromBit(1), "B1", fromBit(0), "B2", fromBit(1))));
        assertTrue(negatedPrime.evaluate(
                Interpretation.of("B0", fromBit(1), "B1", fromBit(1), "B2", fromBit(0))));
        assertFalse(negatedPrime.evaluate(
                Interpretation.of("B0", fromBit(1), "B1", fromBit(1), "B2", fromBit(1))));
    }

    @Test
    void test_evaluate_incompleteFunction() {
        assertFalse(incompletePrime.evaluate(
                Interpretation.of("B0", fromBit(0), "B1", fromBit(0), "B2", fromBit(0))));
        assertThrows(NoSuchElementException.class,
                () -> incompletePrime.evaluate(
                        Interpretation.of("B0", fromBit(0), "B1", fromBit(0), "B2", fromBit(1))));
        
        assertTrue(negatedIncompletePrime.evaluate(
                Interpretation.of("B0", fromBit(0), "B1", fromBit(0), "B2", fromBit(0))));
        assertThrows(NoSuchElementException.class,
                () -> negatedIncompletePrime.evaluate(
                        Interpretation.of("B0", fromBit(0), "B1", fromBit(0), "B2", fromBit(1))));
    }

    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("B0", "B1", "B2"), prime.findVariableNames());
        assertEquals(Set.of("B0", "B1", "B2"), negatedPrime.findVariableNames());
        assertEquals(Set.of("B0", "B1", "B2"), negatedIncompletePrime.findVariableNames());
        assertEquals(Set.of("B0", "B1", "B2"), negatedIncompletePrime.findVariableNames());
    }
}
