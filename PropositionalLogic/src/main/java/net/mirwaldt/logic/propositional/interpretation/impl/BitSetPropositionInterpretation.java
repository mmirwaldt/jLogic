package net.mirwaldt.logic.propositional.interpretation.impl;

import java.util.*;

public class BitSetPropositionInterpretation extends AbstractPropositionInterpretation {
    private final List<String> variableNames;
    private final BitSet bits;
    private final Comparator<String> variableNamesComparator;

    public BitSetPropositionInterpretation(List<String> variableNames, BitSet bits, Comparator<String> variableNamesComparator) {
        this.variableNames = variableNames;
        this.bits = bits;
        this.variableNamesComparator = variableNamesComparator;
    }

    public BitSetPropositionInterpretation(List<String> variableNames, BitSet bits) {
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
        BitSetPropositionInterpretation that = (BitSetPropositionInterpretation) o;
        return Objects.equals(variableNames, that.variableNames) &&
                Objects.equals(variableNamesComparator, that.variableNamesComparator) &&
                Objects.equals(bits, that.bits);
    }
}
