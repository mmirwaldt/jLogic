package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;

import java.util.Map;

public class Interpretations {
    public static Interpretation forMap(Map<String, Boolean> interpretationMap) {
        return new MapInterpretation(interpretationMap);
    }
}
