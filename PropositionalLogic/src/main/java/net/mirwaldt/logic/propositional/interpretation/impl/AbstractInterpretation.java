package net.mirwaldt.logic.propositional.interpretation.impl;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;

import java.util.Objects;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

public abstract class AbstractInterpretation implements Interpretation {
    private int hashCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass().equals(o.getClass())) {
            return equalsSameClass(o);
        }
        
        if (o instanceof Interpretation) {
            Interpretation other = (Interpretation) o;
            if (other.getVariableNames().equals(getVariableNames())) {
                for (String variableName : getVariableNames()) {
                    if (other.get(variableName) != get(variableName)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    protected abstract boolean equalsSameClass(Object o);

    @Override
    public int hashCode() {
        int localHashCode = hashCode;
        if (localHashCode == 0) {
            hashCode = getVariableNames().stream()
                    .sorted()
                    .mapToInt((varName) -> toBit(get(varName)))
                    .reduce(Objects.hash(getVariableNames()),
                            (bit, hash) -> 31 * hash + (bit == 1 ? 1231 : 1237)
                    );
        }
        return localHashCode;
    }
}
