package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static net.mirwaldt.logic.propositional.util.BitUtils.decode;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

public class LongInterpretation implements Interpretation {
    private final List<String> variableNames;
    private final long bits;

    public LongInterpretation(List<String> variableNames, long bits) {
        checkParameters(variableNames);
        this.variableNames = Collections.unmodifiableList(variableNames);
        this.bits = bits;
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

    @Override
    public String asBitsWhitespaceSeparated() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < variableNames.size(); index++) {
            stringBuilder.append(decode(bits, index));
            stringBuilder.append(" ");
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass().equals(o.getClass())) {
            LongInterpretation that = (LongInterpretation) o;
            return bits == that.bits &&
                    Objects.equals(variableNames, that.variableNames);
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

    @Override
    public int hashCode() {
        return getVariableNames().stream()
                .sorted()
                .mapToInt((varName) -> toBit(get(varName)))
                .reduce(Objects.hash(variableNames), (bit, hash) -> 31 * hash + (bit == 1 ? 1231 : 1237));
    }
}
