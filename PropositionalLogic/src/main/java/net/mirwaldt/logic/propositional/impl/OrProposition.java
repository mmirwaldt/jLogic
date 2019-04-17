package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.List;

public class OrProposition extends ListProposition {
    public OrProposition(List<Proposition> propositions) {
        super(propositions, (left, right) -> left | right, "%s ∨ %s");
    }
}
