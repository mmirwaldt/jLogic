package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static net.mirwaldt.logic.propositional.api.Proposition.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListPropositionTest {
    private final Proposition A = variable("A");
    private final Proposition B = variable("B");
    private final Proposition C = variable("C");

    private final Proposition A_AND_B = A.and(B);
    private final Proposition A_OR_B = A.or(B);
    
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
    
    @Test
    void testEquals() {
        assertNotEquals(A_AND_B, null);
        assertEquals(A_AND_B, A_AND_B);
        assertEquals(A_AND_B, A.and(B));
        assertNotEquals(A_AND_B, A.and(B).and(C));
        assertNotEquals(A_AND_B, A);
        assertNotEquals(A_AND_B, B);
        assertNotEquals(A_AND_B, TRUE);
        assertNotEquals(A_AND_B, FALSE);
    }
    
    @Test
    void testHashCode() {
        assertNotEquals(A_AND_B.hashCode(), 0);
        assertEquals(A_AND_B.hashCode(), A_AND_B.hashCode());
        assertEquals(A_AND_B.hashCode(), A.and(B).hashCode());
    }
}
