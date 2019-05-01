package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;

import java.util.Objects;
import java.util.SortedSet;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.toFinalExpression;

public class NotProposition implements Proposition {
    private final Proposition proposition;

    public NotProposition(Proposition proposition) {
        this.proposition = proposition;
    }

    @Override
    public boolean evaluate(PropositionInterpretation interpretation) {
        return !(proposition.evaluate(interpretation));
    }

    @Override
    public String toExpression() {
        final String expression = toFinalExpression(proposition);
        return String.format("¬%s", expression);
    }

    @Override
    public SortedSet<String> findVariableNames() {
        return proposition.findVariableNames();
    }

    public Proposition getProposition() {
        return proposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotProposition that = (NotProposition) o;
        return Objects.equals(proposition, that.proposition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proposition, getClass());
    }
}
