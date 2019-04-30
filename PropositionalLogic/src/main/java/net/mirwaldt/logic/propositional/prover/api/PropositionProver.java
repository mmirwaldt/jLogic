package net.mirwaldt.logic.propositional.prover.api;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;

public interface PropositionProver {
    boolean prove(Proposition proposition);
}
