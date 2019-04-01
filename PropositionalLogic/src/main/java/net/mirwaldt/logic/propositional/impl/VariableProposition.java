package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class VariableProposition implements Proposition {
    private final String variableName;

    public VariableProposition(String variableName) {
        Objects.requireNonNull(variableName);
        this.variableName = variableName;
    }

    public boolean evaluate(Map<String, Boolean> interpretation) {
        final Boolean value = interpretation.get(variableName);
        if(value == null) {
            throw new NoSuchElementException("'" + variableName + "' is missing in the interpretation.");
        } else {
            return value;
        }
    }

    public String toExpression() {
        return variableName;
    }
}
