package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;

/**
 * do not use not-operator implicit in the lambda when you create an unary or a binary proposition!
 * It confuses the expression building algorithm.
 */
public class Propositions {
    public final static ValueProposition FALSE = new ValueProposition(false);
    public final static ValueProposition TRUE = new ValueProposition(true);
    
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

    public static Proposition value(int value) {
        return value(fromBit(value));
    }
    
    public static Proposition not(Proposition proposition) {
        return new NotProposition(proposition);
    }
    
    public static Proposition and(
            Proposition firstProposition, Proposition secondProposition, Proposition... otherPropositions) {
        final List<Proposition> propositions = 
                collectPropositions(firstProposition, secondProposition, otherPropositions);
        return new AndProposition(propositions);
    }

    public static Proposition or(
            Proposition firstProposition, Proposition secondProposition, Proposition... otherPropositions) {
        final List<Proposition> propositions = 
                collectPropositions(firstProposition, secondProposition, otherPropositions);
        return new OrProposition(propositions);
    }
    
    public static Proposition xor(
            Proposition firstProposition, Proposition secondProposition, Proposition... otherPropositions) {
        final List<Proposition> propositions =
                collectPropositions(firstProposition, secondProposition, otherPropositions);
        return new XorProposition(propositions);
    }

    public static Proposition imply(
            Proposition firstProposition, Proposition secondProposition, Proposition... otherPropositions) {
        final List<Proposition> propositions =
                collectPropositions(firstProposition, secondProposition, otherPropositions);
        return new ImplyProposition(propositions);
    }

    public static Proposition iff(
            Proposition firstProposition, Proposition secondProposition, Proposition... otherPropositions) {
        final List<Proposition> propositions =
                collectPropositions(firstProposition, secondProposition, otherPropositions);
        return new IffProposition(propositions);
    }

    public static Proposition nand(
            Proposition leftProposition, Proposition rightProposition, Proposition... otherPropositions) {
        return not(and(leftProposition, rightProposition, otherPropositions));
    }

    public static Proposition nor(
            Proposition leftProposition, Proposition rightProposition, Proposition... otherPropositions) {
        return not(or(leftProposition, rightProposition, otherPropositions));
    }

    public static Proposition function(String functionName, List<String> parameterNames,
                                       List<Interpretation> interpretations, long resultBits) {
        return new FunctionProposition(
                new LongBooleanFunction(functionName, parameterNames, interpretations, resultBits));
    }
    
    private static List<Proposition> collectPropositions(
            Proposition firstProposition, Proposition secondProposition, Proposition[] otherPropositions) {
        final List<Proposition> propositions = new ArrayList<>();
        propositions.add(firstProposition);
        propositions.add(secondProposition);
        Collections.addAll(propositions, otherPropositions);
        return propositions;
    }
}
