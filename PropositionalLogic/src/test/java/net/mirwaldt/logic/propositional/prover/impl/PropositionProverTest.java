package net.mirwaldt.logic.propositional.prover.impl;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import net.mirwaldt.logic.propositional.prover.api.PropositionProver;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.not;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropositionProverTest {
    private final Proposition A = Proposition.variable("A");
    private final Proposition B = Proposition.variable("B");
    private final Proposition C = Proposition.variable("C");
    private final Proposition A_AND_B = A.and(B);
    private final Proposition A_OR_B = A.or(B);
    private final Proposition A_OR_B__OR__NOT_A_AND_NOT_B = 
            A_OR_B.or(not(A).and(not(B))); // always true 
    private final Proposition A_AND_B__AND__A_NAND_B =
            A_AND_B.and(A.nand(B)); // never true 

    private final Proposition A_IFF_B__OR__A_IFF_C__OR__B_IFF_C =
            (A.iff(B)).iff(A.iff(C)).iff(B.iff(C)); // always true

    static Stream<PropositionProver> provers() {
        return Stream.of(new TruthTablePropositionProver(), new ResolutionPropositionProver());
    }

    @ParameterizedTest
    @MethodSource("provers")
    void test_oneVariable(PropositionProver prover) {
        assertFalse(prover.prove(A));
    }

    @ParameterizedTest
    @MethodSource("provers")
    void test_twoVariables(PropositionProver prover) {
        assertFalse(prover.prove(A_OR_B));
        assertTrue(prover.prove(A_OR_B__OR__NOT_A_AND_NOT_B));
    }

    @ParameterizedTest
    @MethodSource("provers")
    void test_threeVariables(PropositionProver prover) {
        assertTrue(prover.prove(A_IFF_B__OR__A_IFF_C__OR__B_IFF_C));
    }
}
