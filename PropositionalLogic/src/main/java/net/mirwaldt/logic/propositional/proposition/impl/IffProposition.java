package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;

import java.util.List;

public class IffProposition extends ListProposition {
    public IffProposition(List<Proposition> propositions) {
        super(propositions, (left, right) -> left == right, "%s ↔ %s");
    }
}
