package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;
import net.mirwaldt.logic.propositional.proposition.api.MultiProposition;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import net.mirwaldt.logic.propositional.util.PropositionUtils;
import net.mirwaldt.logic.propositional.proposition.api.BiBooleanPredicate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Can only be used for left associative operators.
 */
public class ListProposition implements MultiProposition {
    protected final List<Proposition> propositions;
    private final BiBooleanPredicate biBooleanPredicate;
    private final String expressionTemplate;

    public ListProposition(List<Proposition> propositions, BiBooleanPredicate biBooleanPredicate,
                           String expressionTemplate) {
        this.propositions = propositions;
        if(propositions.size() < 2) {
            throw new IllegalArgumentException("Parameter propositions has fewer than 2 elements. " +
                    "It has only " + propositions.size() + " propositions.");
        }
        
        this.biBooleanPredicate = biBooleanPredicate;
        this.expressionTemplate = expressionTemplate;
        
        final int placeholderCount = frequencyOfSubstring(expressionTemplate);
        if(2 != placeholderCount) {
            throw new IllegalArgumentException("expressionTemplate '"
                    + expressionTemplate + "' has " + placeholderCount + " %s-placeholder instead of two!");
        }
    }

    public List<Proposition> getPropositions() {
        return propositions;
    }

    private int frequencyOfSubstring(String expressionTemplate) {
        return expressionTemplate.split("%s", -1).length - 1;
    }

    @Override
    public boolean evaluate(Interpretation interpretation) {
        final Proposition firstProposition = propositions.get(0);
        final Proposition secondProposition = propositions.get(1);
        final boolean firstValue = firstProposition.evaluate(interpretation);
        final boolean secondValue = secondProposition.evaluate(interpretation);
        boolean result = biBooleanPredicate.test(firstValue, secondValue);
        for (int index = 2; index < propositions.size(); index++) {
            final Proposition nextProposition = propositions.get(index);
            final boolean nextValue = nextProposition.evaluate(interpretation);
            result = biBooleanPredicate.test(result, nextValue);
        }
        return result;
    }

    @Override
    public String toExpression() {
        String finalExpressionTemplate = expressionTemplate;
        for (int index = 2; index < propositions.size(); index++) {
            finalExpressionTemplate = String.format(expressionTemplate, finalExpressionTemplate, "%s");
        }
        return String.format(finalExpressionTemplate,
                propositions.stream()
                        .map(PropositionUtils::toFinalExpression)
                        .toArray());
    }

    @Override
    public SortedSet<String> findVariableNames() {
        return propositions.stream()
                .flatMap(proposition -> proposition.findVariableNames().stream())
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListProposition that = (ListProposition) o;
        return Objects.equals(propositions, that.propositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propositions, getClass());
    }
}
