package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.util.api.BooleanPredicate;
import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.Map;

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
    public boolean evaluate(Map<String, Boolean> interpretation) {
        return booleanPredicate.test(proposition.evaluate(interpretation));
    }

    @Override
    public String toExpression() {
        return String.format(expressionTemplate, proposition.toExpression());
    }
}
