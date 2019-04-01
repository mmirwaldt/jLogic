package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class VariableProposition implements Proposition {
    private final String variableId;

    public VariableProposition(String variableId) {
        Objects.requireNonNull(variableId);
        this.variableId = variableId;
    }

    public boolean evaluate(Map<String, Boolean> interpretation) {
        final Boolean value = interpretation.get(variableId);
        if(value == null) {
            throw new NoSuchElementException("'" + variableId + "' is missing in the interpretation.");
        } else {
            return value;
        }
    }

    public String toExpression() {
        return variableId;
    }
}
