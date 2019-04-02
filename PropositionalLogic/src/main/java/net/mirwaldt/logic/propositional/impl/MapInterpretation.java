package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;

import java.util.Map;
import java.util.NoSuchElementException;

public class MapInterpretation implements Interpretation {
    private final Map<String, Boolean> interpretationMap;

    public MapInterpretation(Map<String, Boolean> interpretationMap) {
        this.interpretationMap = interpretationMap;
    }

    @Override
    public boolean get(String variableName) {
        final Boolean value = interpretationMap.get(variableName);
        if(value == null) {
            throw new NoSuchElementException("'" + variableName + "' is missing in the interpretation.");
        } else {
            return value;
        }
    }
}
