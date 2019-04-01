package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.Map;

public class ValueProposition implements Proposition {
    private final boolean value;

    public ValueProposition(boolean value) {
        this.value = value;
    }

    public boolean evaluate(Map<String, Boolean> interpretation) {
        return value;
    }

    @Override
    public String toExpression() {
        return String.valueOf(value);
    }

}
