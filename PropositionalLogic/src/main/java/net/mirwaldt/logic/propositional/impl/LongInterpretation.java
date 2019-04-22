package net.mirwaldt.logic.propositional.impl;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static net.mirwaldt.logic.propositional.util.BitUtils.decode;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;

public class LongInterpretation extends AbstractInterpretation {
    private final List<String> variableNames;
    private final long bits;

    public LongInterpretation(List<String> variableNames, long bits) {
        checkParameters(variableNames);
        this.variableNames = Collections.unmodifiableList(variableNames);
        this.bits = bits;
    }

    @Override
    protected boolean equalsSameClass(Object o) {
        LongInterpretation that = (LongInterpretation) o;
        return bits == that.bits &&
                Objects.equals(variableNames, that.variableNames);
    }
    
    private void checkParameters(List<String> variableNames) {
        if (variableNames.isEmpty()) {
            throw new IllegalArgumentException(
                    "List of variables names must have at least one element!");
        } else if (63 < variableNames.size()) {
            throw new IllegalArgumentException(
                    "More than 63 variables are not supported yet.");
        }
    }

    @Override
    public boolean get(String variableName) {
        final int index = variableNames.indexOf(variableName);
        if (-1 < index) {
            return fromBit(decode(bits, index));
        } else {
            throw new NoSuchElementException("No value available for variable '" + variableName + "'.");
        }
    }

    @Override
    public List<String> getVariableNames() {
        return variableNames;
    }

}
