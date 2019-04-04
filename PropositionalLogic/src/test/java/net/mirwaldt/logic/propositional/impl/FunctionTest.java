package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Map.entry;
import static net.mirwaldt.logic.propositional.impl.Propositions.function;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBits;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionTest {
    private final VariableProposition B0 = Propositions.variable("B0");
    private final VariableProposition B1 = Propositions.variable("B1");
    private final VariableProposition B2 = Propositions.variable("B2");

    private final List<VariableProposition> parameters = Arrays.asList(B0, B1, B2);
    private final Map<List<Boolean>, Boolean> functionValues =
            Map.ofEntries(
                    entry(fromBits(0, 0, 0), fromBit(0)),
                    entry(fromBits(0, 0, 1), fromBit(1)),
                    entry(fromBits(0, 1, 0), fromBit(1)),
                    entry(fromBits(0, 1, 1), fromBit(0)),
                    entry(fromBits(1, 0, 0), fromBit(1)),
                    entry(fromBits(1, 0, 1), fromBit(0)),
                    entry(fromBits(1, 1, 0), fromBit(0)),
                    entry(fromBits(1, 1, 1), fromBit(1))
            );

    private final Map<List<Boolean>, Boolean> incompleteFunctionValues =
            Map.ofEntries(
                    entry(fromBits(0, 0, 0), fromBit(0))
            );

    private final Proposition parity = function("parity", parameters, functionValues);
    private final Proposition incompleteParity = function("parity", parameters, incompleteFunctionValues);

    @Test
    void test_parameterNameNull() {
        assertThrows(NullPointerException.class, () -> function(null, parameters, functionValues));
    }

    @Test
    void test_parametersNull() {
        assertThrows(NullPointerException.class, () -> function("f", null, functionValues));
    }

    @Test
    void test_functionValuesNull() {
        assertThrows(NullPointerException.class, () -> function("f", parameters, null));
    }

    @Test
    void test_parameters_emptyList() {
        assertThrows(IllegalArgumentException.class, () -> function("f", Collections.emptyList(), functionValues));
    }

    @Test
    void test_functionValuesWrong_emptyMap() {
        assertThrows(IllegalArgumentException.class,
                () -> function("f", parameters, Collections.emptyMap()));
    }

    @Test
    void test_functionValuesWrong_emptyArgumentList() {
        assertThrows(IllegalArgumentException.class,
                () -> function("f", parameters, Map.of(Collections.emptyList(), fromBit(0))));
    }

    @Test
    void test_functionValuesWrongNumberOfArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> function("f", parameters, Map.of(fromBits(0, 0), fromBit(0))));
    }

    @Test
    void test_toExpression() {
        assertEquals("parity(B0,B1,B2)", parity.toExpression());
    }

    @Test
    void test_evaluate() {
        assertFalse(parity.evaluate(Interpretation.of("B0", fromBit(0), "B1", fromBit(0), "B2", fromBit(0))));
        assertTrue(parity.evaluate(Interpretation.of("B0", fromBit(0), "B1", fromBit(0), "B2", fromBit(1))));
        assertTrue(parity.evaluate(Interpretation.of("B0", fromBit(0), "B1", fromBit(1), "B2", fromBit(0))));
        assertFalse(parity.evaluate(Interpretation.of("B0", fromBit(0), "B1", fromBit(1), "B2", fromBit(1))));
        assertTrue(parity.evaluate(Interpretation.of("B0", fromBit(1), "B1", fromBit(0), "B2", fromBit(0))));
        assertFalse(parity.evaluate(Interpretation.of("B0", fromBit(1), "B1", fromBit(0), "B2", fromBit(1))));
        assertFalse(parity.evaluate(Interpretation.of("B0", fromBit(1), "B1", fromBit(1), "B2", fromBit(0))));
        assertTrue(parity.evaluate(Interpretation.of("B0", fromBit(1), "B1", fromBit(1), "B2", fromBit(1))));
    }

    @Test
    void test_evaluate_incompleteFunction() {
        assertFalse(incompleteParity.evaluate(Interpretation.of("B0", fromBit(0), "B1", fromBit(0), "B2", fromBit(0))));
        assertThrows(NoSuchElementException.class,
                () -> incompleteParity.evaluate(Interpretation.of("B0", fromBit(0), "B1", fromBit(0), "B2", fromBit(1))));
    }
    
    @Test
    void test_findVariableNames() {
        assertEquals(Set.of("B0", "B1", "B2"), parity.findVariableNames());
    }
}
