package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.MultiProposition;
import net.mirwaldt.logic.propositional.api.Proposition;
import net.mirwaldt.logic.propositional.util.api.BiBooleanPredicate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Can only be used for left associative operators.
 */
public class ListProposition implements MultiProposition {
    private final List<Proposition> propositions;
    private final BiBooleanPredicate biBooleanPredicate;
    private final String expressionTemplate;

    public ListProposition(List<Proposition> propositions,
                           BiBooleanPredicate biBooleanPredicate,
                           String expressionTemplate) {
        this.propositions = propositions;
        this.biBooleanPredicate = biBooleanPredicate;
        this.expressionTemplate = expressionTemplate;
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
                        .map(Proposition::toExpression)
                        .toArray());
    }

    @Override
    public Set<String> findVariableNames() {
        return propositions.stream()
                .flatMap(proposition -> proposition.findVariableNames().stream())
                .collect(Collectors.toSet());
    }
}
