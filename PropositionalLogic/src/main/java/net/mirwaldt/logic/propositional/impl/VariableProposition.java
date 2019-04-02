package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

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

    @Override
    public Set<String> findVariableNames() {
        return Collections.singleton(variableName);
    }

    public String getVariableName() {
        return variableName;
    }
    
    
}
