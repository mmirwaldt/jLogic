package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;

import java.util.List;

public class ImplyProposition extends ListProposition {
    public ImplyProposition(List<Proposition> propositions) {
        super(propositions, (left, right) -> !left | right, "%s → %s");
    }
}
