package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.MultiProposition;
import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.ArrayList;
import java.util.List;

public class AndProposition extends ListProposition {
    public AndProposition(List<Proposition> propositions) {
        super(propositions, (left, right) -> left & right, "%s ∧ %s");
    }

    @Override
    public Proposition and(Proposition otherProposition) {
        if(otherProposition instanceof MultiProposition) {
            return Proposition.and(this, otherProposition);
        } else {
            List<Proposition> newPropositions = new ArrayList<>(propositions);
            newPropositions.add(otherProposition);
            return new AndProposition(newPropositions);
        }
    }
}
