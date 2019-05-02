package net.mirwaldt.logic.propositional.normalizer.impl;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DisjunctivePropositionNormalizerTest {
    private final Proposition A = Proposition.variable("A");
    private final Proposition B = Proposition.variable("B");
    private final Proposition C = Proposition.variable("C");
    private final Proposition A_AND_B = A.and(B);
    private final Proposition A_OR_B = A.or(B);
    
    private final Proposition A_XOR_B__AND__A_XOR_C__AND__B_XOR_C =
            (A.xor(B)).and(A.xor(C)).and(B.xor(C)); // never true
    private final Proposition A_IFF_B__OR__A_IFF_C__OR__B_IFF_C =
            (A.iff(B)).iff(A.iff(C)).iff(B.iff(C)); // always true

    @Test
    void test_oneVariable() {
        assertThrows(NoSuchElementException.class, A::toDNF);
    }

    @Test
    void test_twoVariables() {
        assertEquals(A_AND_B, A_AND_B.toDNF());
        assertEquals((not(A).and(B)).or(A.and(not(B))).or(A.and(B)), A_OR_B.toDNF());
    }

    @Test
    void test_threeVariables() {
        assertThrows(NoSuchElementException.class, A_XOR_B__AND__A_XOR_C__AND__B_XOR_C::toDNF);
        assertEquals(
                (not(A).and(not(B)).and(not(C)))
                        .or(not(A).and(not(B)).and(C))
                        .or(not(A).and(B).and(not(C)))
                        .or(not(A).and(B).and(C))
                        .or(A.and(not(B)).and(not(C)))
                        .or(A.and(not(B)).and(C))
                        .or(A.and(B).and(not(C)))
                        .or(A.and(B).and(C)),
                A_IFF_B__OR__A_IFF_C__OR__B_IFF_C.toDNF());
    }
}
