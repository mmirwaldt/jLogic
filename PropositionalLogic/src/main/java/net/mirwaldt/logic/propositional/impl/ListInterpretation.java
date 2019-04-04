package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class ListInterpretation implements Interpretation {
    private final List<String> variableNames;
    private List<Boolean> values;

    public ListInterpretation(List<String> variableNames) {
        this.variableNames = Collections.unmodifiableList(variableNames);
    }

    @Override
    public boolean get(String variableName) {
        final int index = variableNames.indexOf(variableName);
        if(-1 < index) {
            return values.get(index);
        } else {
            throw new NoSuchElementException("No value available for variable '" + variableName + "'.");
        }
    }

    @Override
    public List<String> getVariableNames() {
        return variableNames;
    }

    public void setValues(List<Boolean> values) {
        if(variableNames.size() != values.size()) {
            throw new IllegalStateException(
                    "Different list sizes: " + variableNames.size() + " vs." + values.size());
        }
        this.values = values;
    }
}
