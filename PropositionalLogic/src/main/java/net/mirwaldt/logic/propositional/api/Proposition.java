package net.mirwaldt.logic.propositional.api;

import net.mirwaldt.logic.propositional.impl.Propositions;

import java.util.Set;

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

    default int evaluateAsBit(Interpretation interpretation) {
        return toBit(evaluate(interpretation));
    }
    
    default Proposition negate() {
        return Propositions.not(this);
    }
    
    default Proposition and(Proposition otherProposition) {
        return Propositions.and(this, otherProposition);
    }
        
    default Proposition or(Proposition otherProposition) {
        return Propositions.or(this, otherProposition);
    }
    
    default Proposition xor(Proposition otherProposition) {
        return Propositions.xor(this, otherProposition);
    }
    
    default Proposition imply(Proposition otherProposition) {
        return Propositions.imply(this, otherProposition);
    }

    default Proposition iff(Proposition otherProposition) {
        return Propositions.iff(this, otherProposition);
    }
    
    default Proposition nand(Proposition otherProposition) {
        return Propositions.nand(this, otherProposition);
    }
    
    default Proposition nor(Proposition otherProposition) {
        return Propositions.nor(this, otherProposition);
    }
}
