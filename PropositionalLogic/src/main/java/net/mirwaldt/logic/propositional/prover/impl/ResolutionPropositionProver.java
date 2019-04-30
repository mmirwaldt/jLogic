package net.mirwaldt.logic.propositional.prover.impl;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import net.mirwaldt.logic.propositional.proposition.impl.AndProposition;
import net.mirwaldt.logic.propositional.proposition.impl.NotProposition;
import net.mirwaldt.logic.propositional.proposition.impl.OrProposition;
import net.mirwaldt.logic.propositional.prover.api.PropositionProver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.not;
import static net.mirwaldt.logic.propositional.proposition.api.Proposition.variable;

public class ResolutionPropositionProver implements PropositionProver {
    @Override
    public boolean prove(Proposition proposition) {
        final AndProposition cnf = (AndProposition) not(proposition).toCNF();
        final List<List<Proposition>> resolvents = toResolvents(cnf);
        return resolve(resolvents);
    }

    private List<List<Proposition>> toResolvents(AndProposition andProposition) {
        List<List<Proposition>> resolvents = new ArrayList<>();
        final List<Proposition> disjunctions = andProposition.getPropositions();
        for (Proposition proposition : disjunctions) {
            final OrProposition orProposition = (OrProposition) proposition;
            final List<Proposition> propositions = orProposition.getPropositions();
            resolvents.add(new ArrayList<>(propositions));
        }
        return resolvents;
    }

    private boolean resolve(List<List<Proposition>> resolvents) {
        for (int leftIndex = 0; leftIndex < resolvents.size(); leftIndex++) {
            final List<Proposition> leftResolvent = resolvents.get(leftIndex);
            for (int rightIndex = leftIndex + 1; rightIndex < resolvents.size(); rightIndex++) {
                final List<Proposition> rightResolvent = resolvents.get(rightIndex);
                final List<Proposition> newResolvent = resolve(leftResolvent, rightResolvent);
                if (newResolvent != null) {
                    if (newResolvent.isEmpty()) {
                        return true;
                    } else if(!resolvents.contains(newResolvent)) {
                        resolvents.add(newResolvent);
                        resolvents.sort(Comparator.comparing(List::size));
                        return resolve(resolvents);
                    }
                }
            }
        }
        return false;
    }

    private List<Proposition> resolve(List<Proposition> leftResolvent, List<Proposition> rightResolvent) {
        String resolvableVariableName = null;
        for (Proposition proposition : leftResolvent) {
            final String variableName = getVariableName(proposition);
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

    private String getVariableName(Proposition proposition) {
        //noinspection OptionalGetWithoutIsPresent
        return proposition.findVariableNames().stream().findFirst().get();
    }

    private List<Proposition> createNewResolvent(
            List<Proposition> leftResolvent, List<Proposition> rightResolvent, String resolvableVariableName) {
        final List<Proposition> newResolvent = new ArrayList<>();
        newResolvent.addAll(leftResolvent);
        newResolvent.addAll(rightResolvent);
        final Proposition resolvableVariable = variable(resolvableVariableName);
        newResolvent.remove(resolvableVariable);
        newResolvent.remove(not(resolvableVariable));
        newResolvent.sort(Comparator.comparing(this::getVariableName));
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
