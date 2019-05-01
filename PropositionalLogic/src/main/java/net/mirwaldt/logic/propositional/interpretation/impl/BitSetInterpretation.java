package net.mirwaldt.logic.propositional.interpretation.impl;

import java.util.*;

public class BitSetInterpretation extends AbstractInterpretation {
    private final List<String> variableNames;
    private final BitSet bits;
    private final Comparator<String> variableNamesComparator;

    public BitSetInterpretation(List<String> variableNames, BitSet bits, Comparator<String> variableNamesComparator) {
        this.variableNames = variableNames;
        this.bits = bits;
        this.variableNamesComparator = variableNamesComparator;
    }

    public BitSetInterpretation(List<String> variableNames, BitSet bits) {
        this(variableNames, bits, Comparator.naturalOrder());
    }

    @Override
    public boolean get(String variableName) {
        final int index = Collections.binarySearch(variableNames, variableName, variableNamesComparator);
        if (-1 < index) {
            return bits.get(index);
        } else {
            throw new NoSuchElementException("No value available for variable '" + variableName + "'.");
        }
    }

    @Override
    public List<String> getVariableNames() {
        return variableNames;
    }

    @Override
    protected boolean equalsSameClass(Object o) {
        BitSetInterpretation that = (BitSetInterpretation) o;
        return Objects.equals(variableNames, that.variableNames) &&
                Objects.equals(variableNamesComparator, that.variableNamesComparator) &&
                Objects.equals(bits, that.bits);
    }
}
