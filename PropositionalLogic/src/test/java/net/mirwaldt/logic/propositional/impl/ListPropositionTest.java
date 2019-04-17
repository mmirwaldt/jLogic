package net.mirwaldt.logic.propositional.impl;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static net.mirwaldt.logic.propositional.impl.Propositions.variable;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListPropositionTest {
    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new ListProposition(Collections.emptyList(), (left, right) -> left, "%s left %s"));
        assertThrows(IllegalArgumentException.class,
                () -> new ListProposition(Collections.singletonList(variable("A")),
                        (left, right) -> left, "%s left %s"));
        assertThrows(IllegalArgumentException.class,
                () -> new ListProposition(asList(variable("A"), variable("B")),
                        (left, right) -> left, "noPlaceHolder"));
        assertThrows(IllegalArgumentException.class,
                () -> new ListProposition(asList(variable("A"), variable("B")),
                        (left, right) -> left, "%s"));

        new ListProposition(asList(variable("A"), variable("B")),
                (left, right) -> left, "%s left %s");
    }
}
