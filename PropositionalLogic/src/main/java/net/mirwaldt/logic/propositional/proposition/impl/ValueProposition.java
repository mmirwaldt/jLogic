package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

public class ValueProposition implements Proposition {
    private final boolean value;

    public ValueProposition(boolean value) {
        this.value = value;
    }

    public boolean evaluate(Interpretation interpretation) {
        return value;
    }

    @Override
    public String toExpression() {
        return String.valueOf(toBit(value));
    }

    @Override
    public Set<String> findVariableNames() {
        return Collections.emptySet();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueProposition that = (ValueProposition) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
