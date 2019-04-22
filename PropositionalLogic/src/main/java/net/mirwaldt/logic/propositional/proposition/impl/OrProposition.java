package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;

import java.util.ArrayList;
import java.util.List;

public class OrProposition extends ListProposition {
    private OrProposition(List<Proposition> propositions) {
        super(propositions, (left, right) -> left | right, "%s ∨ %s");
    }

    public static OrProposition create(List<Proposition> propositions) {
        List<Proposition> newPropositions = new ArrayList<>();
        for (Proposition proposition : propositions) {
            if(proposition instanceof OrProposition) {
                final OrProposition orProposition = (OrProposition) proposition;
                newPropositions.addAll(orProposition.propositions);
            } else {
                newPropositions.add(proposition);
            }
        }
        return new OrProposition(newPropositions);
    }
}
