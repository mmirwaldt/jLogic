package net.mirwaldt.logic.propositional.normalizer.api;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;

public interface PropositionNormalizer {
    Proposition normalize(Proposition proposition);
}
