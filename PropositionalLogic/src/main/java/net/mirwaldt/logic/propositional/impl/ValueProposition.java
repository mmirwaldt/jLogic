package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

public class ValueProposition implements Proposition {
    private final boolean value;

    public ValueProposition(boolean value) {
        this.value = value;
    }

    public boolean evaluate(Interpretation interpretation) {
        return value;
    }

    @Override
    public String toExpression() {
        return String.valueOf(toBit(value));
    }
}
