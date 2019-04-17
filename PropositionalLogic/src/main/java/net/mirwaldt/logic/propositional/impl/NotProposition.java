package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.Objects;
import java.util.Set;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.toFinalExpression;

public class NotProposition implements Proposition {
    private final Proposition proposition;

    public NotProposition(Proposition proposition) {
        this.proposition = proposition;
    }

    @Override
    public boolean evaluate(Interpretation interpretation) {
        return !(proposition.evaluate(interpretation));
    }

    @Override
    public String toExpression() {
        final String expression = toFinalExpression(proposition);
        return String.format("¬%s", expression);
    }

    @Override
    public Set<String> findVariableNames() {
        return proposition.findVariableNames();
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
