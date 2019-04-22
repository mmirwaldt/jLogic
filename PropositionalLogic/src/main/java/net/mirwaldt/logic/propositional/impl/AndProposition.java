package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.ArrayList;
import java.util.List;

public class AndProposition extends ListProposition {
    private AndProposition(List<Proposition> propositions) {
        super(propositions, (left, right) -> left & right, "%s ∧ %s");
    }

    public static AndProposition create(List<Proposition> propositions) {
        List<Proposition> newPropositions = new ArrayList<>();
        for (Proposition proposition : propositions) {
            if (proposition instanceof AndProposition) {
                final AndProposition andProposition = (AndProposition) proposition;
                newPropositions.addAll(andProposition.propositions);
            } else {
                newPropositions.add(proposition);
            }
        }
        return new AndProposition(newPropositions);
    }
}
