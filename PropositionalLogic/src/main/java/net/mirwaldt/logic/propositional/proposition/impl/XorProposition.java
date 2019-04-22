package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;

import java.util.List;

public class XorProposition extends ListProposition {
    public XorProposition(List<Proposition> propositions) {
        super(propositions, (left, right) -> left ^ right, "%s ⩒ %s");
    }
}
