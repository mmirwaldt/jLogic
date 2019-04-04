package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;

public class LongInterpretation implements Interpretation {
    private final List<String> variableNames;
    private final long bits;

    public LongInterpretation(List<String> variableNames, long bits) {
        checkParameters(variableNames);
        this.variableNames = Collections.unmodifiableList(variableNames);
        this.bits = bits;
    }

    private void checkParameters(List<String> variableNames) {
        if(variableNames.isEmpty()) {
            throw new IllegalArgumentException(
                    "List of variables names must have at least one element!");
        } else if(63 < variableNames.size()) {
            throw new IllegalArgumentException(
                    "More than 63 variables are not supported yet.");
        }
    }

    @Override
    public boolean get(String variableName) {
        final int index = variableNames.indexOf(variableName);
        if(-1 < index) {
            long bitMask = 1L << index;
            int bit = (int) ((bits & bitMask) >> index);
            return fromBit(bit);
        } else {
            throw new NoSuchElementException("No value available for variable '" + variableName + "'.");
        }
    }

    @Override
    public List<String> getVariableNames() {
        return variableNames;
    }
}
