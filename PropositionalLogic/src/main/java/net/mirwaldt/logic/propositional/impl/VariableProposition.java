package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.Objects;

public class VariableProposition implements Proposition {
    private final String variableName;

    public VariableProposition(String variableName) {
        Objects.requireNonNull(variableName);
        this.variableName = variableName;
    }

    public boolean evaluate(Interpretation interpretation) {
        return interpretation.get(variableName);
    }

    public String toExpression() {
        return variableName;
    }
}
