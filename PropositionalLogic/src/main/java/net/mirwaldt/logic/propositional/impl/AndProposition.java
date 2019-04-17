package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.List;

public class AndProposition extends ListProposition {
    public AndProposition(List<Proposition> propositions) {
        super(propositions, (left, right) -> left & right, "%s ∧ %s");
    }
}
