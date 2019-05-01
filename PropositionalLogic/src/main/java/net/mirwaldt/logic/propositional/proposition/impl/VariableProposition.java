package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import net.mirwaldt.logic.propositional.util.SingletonSortedSet;

import java.util.*;

public class VariableProposition implements Proposition {
    private final String variableName;

    public VariableProposition(String variableName) {
        Objects.requireNonNull(variableName);
        this.variableName = variableName;
    }

    public boolean evaluate(PropositionInterpretation interpretation) {
        return interpretation.get(variableName);
    }

    public String toExpression() {
        return variableName;
    }

    @Override
    public SortedSet<String> findVariableNames() {
        return SingletonSortedSet.singletonSortedSet(variableName, Comparator.naturalOrder());
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableProposition that = (VariableProposition) o;
        return Objects.equals(variableName, that.variableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName);
    }
}
