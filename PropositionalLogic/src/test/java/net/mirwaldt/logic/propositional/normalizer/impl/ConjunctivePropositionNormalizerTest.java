package net.mirwaldt.logic.propositional.normalizer.impl;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConjunctivePropositionNormalizerTest {
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
        assertThrows(NoSuchElementException.class, A::toCNF);
    }

    @Test
    void test_twoVariables() {
        assertEquals((A.or(B)).and(A.or(not(B)).and(not(A).or(B))), A_AND_B.toCNF());
        assertEquals(A_OR_B, A_OR_B.toCNF());
    }

    @Test
    void test_threeVariables() {
        assertThrows(NoSuchElementException.class, A_IFF_B__OR__A_IFF_C__OR__B_IFF_C::toCNF);
        assertEquals(
                (A.or(B).or(C))
                        .and(A.or(B).or(not(C)))
                        .and(A.or(not(B)).or(C))
                        .and(A.or(not(B)).or(not(C)))
                        .and(not(A).or(B).or(C))
                        .and(not(A).or(B).or(not(C)))
                        .and(not(A).or(not(B)).or(C))
                        .and(not(A).or(not(B)).or(not(C))),
                A_XOR_B__AND__A_XOR_C__AND__B_XOR_C.toCNF());
    }
}
