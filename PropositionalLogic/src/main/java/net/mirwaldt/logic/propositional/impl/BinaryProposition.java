package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.MultiProposition;
import net.mirwaldt.logic.propositional.api.Proposition;
import net.mirwaldt.logic.propositional.util.api.BiBooleanPredicate;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.toFinalExpression;

public class BinaryProposition implements MultiProposition {
    private final Proposition leftProposition;
    private final Proposition rightProposition;
    private final BiBooleanPredicate biBooleanPredicate;
    private final String expressionTemplate;

    public BinaryProposition(Proposition leftProposition, Proposition rightProposition, 
                             BiBooleanPredicate biBooleanPredicate, String expressionTemplate) {
        this.leftProposition = leftProposition;
        this.rightProposition = rightProposition;
        this.biBooleanPredicate = biBooleanPredicate;
        this.expressionTemplate = expressionTemplate;
    }

    @Override
    public boolean evaluate(Interpretation interpretation) {
        return biBooleanPredicate.test(leftProposition.evaluate(interpretation), 
                rightProposition.evaluate(interpretation));
    }

    @Override
    public String toExpression() {
        final String leftExpression = toFinalExpression(leftProposition);
        final String rightExpression = toFinalExpression(rightProposition);
        return String.format(expressionTemplate, leftExpression, rightExpression);
    }

    @Override
    public Set<String> findVariableNames() {
        return Stream.of(leftProposition, rightProposition)
                .flatMap(proposition -> proposition.findVariableNames().stream())
                .collect(Collectors.toSet());
    }
}
