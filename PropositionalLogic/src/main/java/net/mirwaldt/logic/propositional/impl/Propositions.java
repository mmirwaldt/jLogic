package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.BinaryPropositionNegator;
import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import net.mirwaldt.logic.propositional.util.api.BiBooleanPredicate;
import net.mirwaldt.logic.propositional.util.api.BooleanPredicate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * do not use not-operator implicit in the lambda when you create and unary or binary proposition!
 * It confuses the expression building algorithm and the negating.
 */
public class Propositions {
    public final static ValueProposition FALSE = new ValueProposition(false);
    public final static ValueProposition TRUE = new ValueProposition(true);

    private final static BiBooleanPredicate AND_OPERATOR = (left, right) -> left & right;
    private final static BiBooleanPredicate OR_OPERATOR = (left, right) -> left | right;
    private final static String AND_EXPRESSION_TEMPLATE = "%s ∧ %s";
    private final static String OR_EXPRESSION_TEMPLATE = "%s ∨ %s";
    
    // DeMorgan's Laws
    private final static BinaryPropositionNegator BINARY_AND_NEGATOR = (left, right) -> or(negate(left), negate(right));
    private final static BinaryPropositionNegator BINARY_OR_NEGATOR = (left, right) -> and(negate(left), negate(right));

    public static VariableProposition variable(String variableId) {
        return new VariableProposition(variableId);
    }

    public static Proposition value(boolean value) {
        if (value) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    public static Proposition unary(
            Proposition proposition, BooleanPredicate booleanPredicate, String expressionTemplate) {
        return new UnaryProposition(proposition, booleanPredicate, expressionTemplate);
    }

    public static Proposition negate(Proposition proposition) {
        return unary(proposition, (BooleanPredicate) ((value) -> !value), "¬%s");
    }

    public static Proposition binary(Proposition leftProposition, Proposition rightProposition,
                                     BiBooleanPredicate biBooleanPredicate, String expressionTemplate,
                                     BinaryPropositionNegator negator) {
        return new BinaryProposition(leftProposition, rightProposition, biBooleanPredicate, expressionTemplate, negator);
    }

    public static Proposition and(Proposition leftProposition, Proposition rightProposition) {
        return binary(leftProposition, rightProposition, AND_OPERATOR, AND_EXPRESSION_TEMPLATE, BINARY_AND_NEGATOR);
    }

    public static Proposition or(Proposition leftProposition, Proposition rightProposition) {
        return binary(leftProposition, rightProposition, OR_OPERATOR, OR_EXPRESSION_TEMPLATE, BINARY_OR_NEGATOR);
    }

    public static Proposition xor(Proposition leftProposition, Proposition rightProposition) {
        return binary(leftProposition, rightProposition, (left, right) -> left ^ right, "%s ⩒ %s",
                Propositions::iff);
    }

    public static Proposition imply(Proposition leftProposition, Proposition rightProposition) {
        return binary(leftProposition, rightProposition, (left, right) -> !left | right, "%s → %s",
                (left, right) -> and(left, negate(right)));
    }

    public static Proposition iff(Proposition leftProposition, Proposition rightProposition) {
        return binary(leftProposition, rightProposition, (left, right) -> left == right, "%s ↔ %s",
                Propositions::xor);
    }

    public static Proposition nand(Proposition leftProposition, Proposition rightProposition) {
        return negate(and(leftProposition, rightProposition));
    }

    public static Proposition nor(Proposition leftProposition, Proposition rightProposition) {
        return negate(or(leftProposition, rightProposition));
    }

    public static Proposition function(String functionName, List<String> parameterNames,
                                       List<Interpretation> interpretations, long resultBits) {
        return new FunctionProposition(
                new LongBooleanFunction(functionName, parameterNames, interpretations, resultBits));
    }

    public static Proposition and(Proposition firstProposition, Proposition secondProposition,
                                  Proposition thirdProposition, Proposition... otherPropositions) {
        final List<Proposition> propositions = new ArrayList<>();
        propositions.add(firstProposition);
        propositions.add(secondProposition);
        propositions.add(thirdProposition);
        Collections.addAll(propositions, otherPropositions);
        return new ListProposition(propositions, AND_OPERATOR, AND_EXPRESSION_TEMPLATE);
    }

    public static Proposition or(Proposition firstProposition, Proposition secondProposition,
                                 Proposition thirdProposition, Proposition... otherPropositions) {
        final List<Proposition> propositions = new ArrayList<>();
        propositions.add(firstProposition);
        propositions.add(secondProposition);
        propositions.add(thirdProposition);
        Collections.addAll(propositions, otherPropositions);
        return new ListProposition(propositions, OR_OPERATOR, OR_EXPRESSION_TEMPLATE);
    }
}
