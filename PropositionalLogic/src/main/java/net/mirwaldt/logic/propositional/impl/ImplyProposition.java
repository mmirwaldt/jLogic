package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.List;

public class ImplyProposition extends ListProposition {
    public ImplyProposition(List<Proposition> propositions) {
        super(propositions, (left, right) -> !left | right, "%s → %s");
    }
}
