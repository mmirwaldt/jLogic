package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import net.mirwaldt.logic.propositional.util.api.BooleanPredicate;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static net.mirwaldt.logic.propositional.impl.Propositions.TRUE;
import static org.junit.jupiter.api.Assertions.*;

public class UnaryPropositionTest {
    private final Proposition identity = Propositions.unary(TRUE,
            BooleanPredicate.identity(), "%s");
    private final Proposition negatedIdentity = identity.negate();

    @Test
    void test_wrongExpressionTemplate() {
        assertThrows(IllegalArgumentException.class,
                () -> Propositions.unary(TRUE, BooleanPredicate.identity(), "#"));
    }
    
    @Test
    void test_expression() {
        assertEquals(String.valueOf(1), identity.toExpression());
    }

    @Test
    void test_evaluate() {
        assertTrue(identity.evaluate(Interpretation.of("A", 1)));
    }

    @Test
    void test_findVariableNames() {
        assertEquals(Collections.emptySet(), identity.findVariableNames());
    }
}
