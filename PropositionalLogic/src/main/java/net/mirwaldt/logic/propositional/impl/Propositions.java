package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.util.api.BiBooleanPredicate;
import net.mirwaldt.logic.propositional.util.api.BooleanPredicate;
import net.mirwaldt.logic.propositional.api.Proposition;

public class Propositions {
    public final static ValueProposition FALSE = new ValueProposition(false);
    public final static ValueProposition TRUE = new ValueProposition(true);

    public final static Proposition variable(String variableId) {
        return new VariableProposition(variableId);
    }

    public final static Proposition unary(
            Proposition proposition, BooleanPredicate booleanPredicate, String expressionTemplate) {
        return new UnaryProposition(proposition, booleanPredicate, expressionTemplate);
    }
    
    public final static Proposition negate(Proposition proposition) {
        return new UnaryProposition(proposition, (value)->!value, "¬%s");
    }

    public final static Proposition binary(Proposition leftProposition, Proposition rightProposition, 
                                           BiBooleanPredicate biBooleanPredicate, String expressionTemplate) {
        return new BinaryProposition(leftProposition, rightProposition, biBooleanPredicate, expressionTemplate);
    }
    
    public final static Proposition and(Proposition leftProposition, Proposition rightProposition) {
        return new BinaryProposition(leftProposition, rightProposition, 
                (left, right)-> left & right, "%s ∧ %s");
    }

    public final static Proposition or(Proposition leftProposition, Proposition rightProposition) {
        return new BinaryProposition(leftProposition, rightProposition,
                (left, right)-> left | right, "%s ∨ %s");
    }
    
    public final static Proposition xor(Proposition leftProposition, Proposition rightProposition) {
        return new BinaryProposition(leftProposition, rightProposition,
                (left, right)-> left ^ right, "%s ⩒ %s");
    }
    
    public final static Proposition imply(Proposition leftProposition, Proposition rightProposition) {
        return new BinaryProposition(leftProposition, rightProposition,
                (left, right)-> !left | right, "%s → %s");
    }
    
    public final static Proposition ifAndOnlyIf(Proposition leftProposition, Proposition rightProposition) {
        return new BinaryProposition(leftProposition, rightProposition,
                (left, right)-> left == right, "%s ↔ %s");
    }
    
    public final static Proposition inBrackets(Proposition proposition) {
        return new UnaryProposition(proposition, BooleanPredicate.identity(), "(%s)");
    }
}
