package net.mirwaldt.logic.propositional.prover.impl;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import net.mirwaldt.logic.propositional.proposition.impl.AndProposition;
import net.mirwaldt.logic.propositional.proposition.impl.NotProposition;
import net.mirwaldt.logic.propositional.proposition.impl.OrProposition;
import net.mirwaldt.logic.propositional.prover.api.PropositionProver;
import net.mirwaldt.logic.propositional.util.SingleVariablePropositionComparator;

import java.util.*;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.not;
import static net.mirwaldt.logic.propositional.proposition.api.Proposition.variable;

public class ResolutionPropositionProver implements PropositionProver {
    @Override
    public boolean prove(Proposition proposition) {
        final AndProposition cnf = (AndProposition) not(proposition).toCNF();
        final List<SortedSet<Proposition>> resolvents = toResolvents(cnf);
        return resolve(resolvents);
    }

    private List<SortedSet<Proposition>> toResolvents(AndProposition andProposition) {
        List<SortedSet<Proposition>> resolvents = new ArrayList<>();
        final List<Proposition> disjunctions = andProposition.getPropositions();
        for (Proposition proposition : disjunctions) {
            final OrProposition orProposition = (OrProposition) proposition;
            final List<Proposition> propositions = orProposition.getPropositions();
            final SortedSet<Proposition> resolvent = new TreeSet<>(new SingleVariablePropositionComparator());
            resolvent.addAll(propositions);
            resolvents.add(resolvent);
        }
        return resolvents;
    }

    private boolean resolve(List<SortedSet<Proposition>> resolvents) {
        for (int leftIndex = 0; leftIndex < resolvents.size(); leftIndex++) {
            final SortedSet<Proposition> leftResolvent = resolvents.get(leftIndex);
            for (int rightIndex = leftIndex + 1; rightIndex < resolvents.size(); rightIndex++) {
                final SortedSet<Proposition> rightResolvent = resolvents.get(rightIndex);
                final SortedSet<Proposition> newResolvent = resolve(leftResolvent, rightResolvent);
                if (newResolvent != null) {
                    if (newResolvent.isEmpty()) {
                        return true;
                    } else if(!resolvents.contains(newResolvent)) {
                        resolvents.add(newResolvent);
                        resolvents.sort(Comparator.comparing(SortedSet::size));
                        return resolve(resolvents);
                    }
                }
            }
        }
        return false;
    }

    private SortedSet<Proposition> resolve(
            SortedSet<Proposition> leftResolvent, SortedSet<Proposition> rightResolvent) {
        String resolvableVariableName = null;
        for (Proposition proposition : leftResolvent) {
            final String variableName = proposition.findVariableNames().first();
            final Proposition opposite = turnToOpposite(proposition);

            if (rightResolvent.contains(opposite)) {
                if (resolvableVariableName == null) {
                    resolvableVariableName = variableName;
                } else { // more than one variable cannot be resolved
                    return null;
                }
            }
        }

        if (resolvableVariableName == null) {
            return null;
        } else {
            return createNewResolvent(leftResolvent, rightResolvent, resolvableVariableName);
        }
    }

    private SortedSet<Proposition> createNewResolvent(
            SortedSet<Proposition> leftResolvent, SortedSet<Proposition> rightResolvent, 
            String resolvableVariableName) {
        final SortedSet<Proposition> newResolvent = new TreeSet<>(new SingleVariablePropositionComparator());
        newResolvent.addAll(leftResolvent);
        newResolvent.addAll(rightResolvent);
        final Proposition resolvableVariable = variable(resolvableVariableName);
        newResolvent.remove(resolvableVariable);
        newResolvent.remove(not(resolvableVariable));
        return newResolvent;
    }

    private Proposition turnToOpposite(Proposition proposition) {
        final Proposition opposite;
        if (proposition.getClass().equals(NotProposition.class)) {
            final NotProposition notProposition = (NotProposition) proposition;
            opposite = notProposition.getProposition();
        } else {
            opposite = not(proposition);
        }
        return opposite;
    }
}
