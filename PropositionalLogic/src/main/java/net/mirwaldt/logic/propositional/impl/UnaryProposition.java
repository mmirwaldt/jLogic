package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import net.mirwaldt.logic.propositional.util.LambdaObjects;
import net.mirwaldt.logic.propositional.util.api.BooleanPredicate;

import java.util.Objects;
import java.util.Set;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.toFinalExpression;

public class UnaryProposition implements Proposition {
    private final Proposition proposition;
    private final BooleanPredicate booleanPredicate;
    private final String expressionTemplate;

    public UnaryProposition(Proposition proposition, BooleanPredicate booleanPredicate, String expressionTemplate) {
        this.proposition = proposition;
        this.booleanPredicate = booleanPredicate;
        
        if(!expressionTemplate.contains("%s")) {
            throw new IllegalArgumentException("expressionTemplate '" 
                    + expressionTemplate + "' misses %s-placeholder!");
        }
        this.expressionTemplate = expressionTemplate;
    }

    @Override
    public boolean evaluate(Interpretation interpretation) {
        return booleanPredicate.test(proposition.evaluate(interpretation));
    }

    @Override
    public String toExpression() {
        final String expression = toFinalExpression(proposition);
        return String.format(expressionTemplate, expression);
    }

    @Override
    public Set<String> findVariableNames() {
        return proposition.findVariableNames();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnaryProposition that = (UnaryProposition) o;
        return Objects.equals(proposition, that.proposition) &&
                LambdaObjects.equals(booleanPredicate, that.booleanPredicate) &&
                Objects.equals(expressionTemplate, that.expressionTemplate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proposition, LambdaObjects.hash(booleanPredicate), expressionTemplate);
    }
}
