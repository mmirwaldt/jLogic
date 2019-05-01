package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.proposition.api.BooleanFunction;
import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class FunctionProposition implements Proposition {
    private final BooleanFunction booleanFunction;

    public FunctionProposition(BooleanFunction booleanFunction) {
        this.booleanFunction = booleanFunction;
    }

    @Override
    public boolean evaluate(PropositionInterpretation interpretation) {
        return booleanFunction.getResult(interpretation);
    }

    public List<PropositionInterpretation> getInterpretations() {
        return booleanFunction.getInterpretations();
    }

    @Override
    public String toExpression() {
        return booleanFunction.getFunctionSignature();
    }

    @Override
    public SortedSet<String> findVariableNames() {
        return new TreeSet<>(booleanFunction.getParameterNames());
    }
}
