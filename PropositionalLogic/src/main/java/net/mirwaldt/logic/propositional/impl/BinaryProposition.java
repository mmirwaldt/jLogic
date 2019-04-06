package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.MultiProposition;
import net.mirwaldt.logic.propositional.api.Proposition;
import net.mirwaldt.logic.propositional.api.BinaryPropositionNegator;
import net.mirwaldt.logic.propositional.util.LambdaObjects;
import net.mirwaldt.logic.propositional.util.api.BiBooleanPredicate;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.toFinalExpression;

public class BinaryProposition implements MultiProposition {
    private final Proposition leftProposition;
    private final Proposition rightProposition;
    private final BiBooleanPredicate biBooleanPredicate;
    private final String expressionTemplate;
    private final BinaryPropositionNegator negator;

    public BinaryProposition(Proposition leftProposition, Proposition rightProposition, 
                             BiBooleanPredicate biBooleanPredicate, String expressionTemplate,
                             BinaryPropositionNegator negator) {
        this.leftProposition = leftProposition;
        this.rightProposition = rightProposition;
        this.biBooleanPredicate = biBooleanPredicate;
        this.expressionTemplate = expressionTemplate;
        this.negator = negator;

        final int placeholderCount = frequencyOfSubstring(expressionTemplate);
        if(2 != placeholderCount) {
            throw new IllegalArgumentException("expressionTemplate '"
                    + expressionTemplate + "' has " + placeholderCount + " %s-placeholder instead of two!");
        }
    }

    private int frequencyOfSubstring(String expressionTemplate) {
        return expressionTemplate.split("%s", -1).length - 1;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryProposition that = (BinaryProposition) o;
        return Objects.equals(leftProposition, that.leftProposition) &&
                Objects.equals(rightProposition, that.rightProposition) &&
                LambdaObjects.equals(biBooleanPredicate, that.biBooleanPredicate) &&
                LambdaObjects.equals(negator, that.negator) &&
                Objects.equals(expressionTemplate, that.expressionTemplate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftProposition, rightProposition, 
                LambdaObjects.hash(biBooleanPredicate), LambdaObjects.hash(negator), expressionTemplate);
    }

    @Override
    public Proposition negate() {
        return negator.apply(leftProposition, rightProposition);
    }
}
