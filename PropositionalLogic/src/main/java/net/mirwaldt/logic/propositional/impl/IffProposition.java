package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.List;

public class IffProposition extends ListProposition {
    public IffProposition(List<Proposition> propositions) {
        super(propositions, (left, right) -> left == right, "%s ↔ %s");
    }
}
