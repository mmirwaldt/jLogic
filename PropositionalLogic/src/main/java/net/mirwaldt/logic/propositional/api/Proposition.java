package net.mirwaldt.logic.propositional.api;

import net.mirwaldt.logic.propositional.impl.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

public interface Proposition {
    /**
     * evaluates an interpretation (the values for propositional variables)
     * @param interpretation the values for propositional variables
     * @return the evaluated value for the proposition
     * @throws java.util.NoSuchElementException if map contains not the needed values for the evaluation 
     */
    boolean evaluate(Interpretation interpretation);
    
    /**
     * converts proposition to expression string
     * @return the proposition as expression string
     */
    String toExpression();
    
    Set<String> findVariableNames();
    
    ValueProposition FALSE = new ValueProposition(false);
    ValueProposition TRUE = new ValueProposition(true);

    static VariableProposition variable(String variableId) {
        return new VariableProposition(variableId);
    }

    static Proposition value(boolean value) {
        if (value) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    static Proposition value(int value) {
        return value(fromBit(value));
    }

    default int evaluateAsBit(Interpretation interpretation) {
        return toBit(evaluate(interpretation));
    }
    
    default Proposition negate() {
        return not(this);
    }
    
    default Proposition and(Proposition otherProposition) {
        return and(this, otherProposition);
    }
        
    default Proposition or(Proposition otherProposition) {
        return or(this, otherProposition);
    }
    
    default Proposition xor(Proposition otherProposition) {
        return xor(this, otherProposition);
    }
    
    default Proposition imply(Proposition otherProposition) {
        return imply(this, otherProposition);
    }

    default Proposition iff(Proposition otherProposition) {
        return iff(this, otherProposition);
    }
    
    default Proposition nand(Proposition otherProposition) {
        return nand(this, otherProposition);
    }
    
    default Proposition nor(Proposition otherProposition) {
        return nor(this, otherProposition);
    }
    
    static Proposition not(Proposition proposition) {
        return new NotProposition(proposition);
    }

    static Proposition and(
            Proposition firstProposition, Proposition secondProposition, Proposition... otherPropositions) {
        final List<Proposition> propositions =
                collectPropositions(firstProposition, secondProposition, otherPropositions);
        return new AndProposition(propositions);
    }

    static Proposition or(
            Proposition firstProposition, Proposition secondProposition, Proposition... otherPropositions) {
        final List<Proposition> propositions =
                collectPropositions(firstProposition, secondProposition, otherPropositions);
        return new OrProposition(propositions);
    }

    static Proposition xor(
            Proposition firstProposition, Proposition secondProposition, Proposition... otherPropositions) {
        final List<Proposition> propositions =
                collectPropositions(firstProposition, secondProposition, otherPropositions);
        return new XorProposition(propositions);
    }

    static Proposition imply(
            Proposition firstProposition, Proposition secondProposition, Proposition... otherPropositions) {
        final List<Proposition> propositions =
                collectPropositions(firstProposition, secondProposition, otherPropositions);
        return new ImplyProposition(propositions);
    }

    static Proposition iff(
            Proposition firstProposition, Proposition secondProposition, Proposition... otherPropositions) {
        final List<Proposition> propositions =
                collectPropositions(firstProposition, secondProposition, otherPropositions);
        return new IffProposition(propositions);
    }

    static Proposition nand(
            Proposition leftProposition, Proposition rightProposition, Proposition... otherPropositions) {
        return not(and(leftProposition, rightProposition, otherPropositions));
    }

    static Proposition nor(
            Proposition leftProposition, Proposition rightProposition, Proposition... otherPropositions) {
        return not(or(leftProposition, rightProposition, otherPropositions));
    }

    static Proposition function(String functionName, List<String> parameterNames,
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
