package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.BooleanFunction;
import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.HashSet;
import java.util.Set;

public class FunctionProposition implements Proposition {
    private final BooleanFunction booleanFunction;

    public FunctionProposition(BooleanFunction booleanFunction) {
        this.booleanFunction = booleanFunction;
    }

    @Override
    public boolean evaluate(Interpretation interpretation) {
        return booleanFunction.getResult(interpretation);
    }

    @Override
    public String toExpression() {
        return booleanFunction.getFunctionSignature();
    }

    @Override
    public Set<String> findVariableNames() {
        return new HashSet<>(booleanFunction.getParameterNames());
    }
}
