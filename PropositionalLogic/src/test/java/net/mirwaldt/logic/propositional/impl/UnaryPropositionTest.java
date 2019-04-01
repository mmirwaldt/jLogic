package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.util.api.BooleanPredicate;
import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static net.mirwaldt.logic.propositional.impl.Propositions.TRUE;
import static org.junit.jupiter.api.Assertions.*;

public class UnaryPropositionTest {
    private final Proposition identity = Propositions.unary(TRUE,
            BooleanPredicate.identity(), "%s");

    @Test
    void test_wrongExpressionTemplate() {
        assertThrows(IllegalArgumentException.class,
                () -> Propositions.unary(TRUE, BooleanPredicate.identity(), "#"));
    }
    
    @Test
    void test_expression() {
        assertEquals(String.valueOf(true), identity.toExpression());
    }

    @Test
    void test_evaluate() {
        assertTrue(identity.evaluate(Collections.emptyMap()));
    }
}
