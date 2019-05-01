package net.mirwaldt.logic.propositional.normalizer.impl;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import net.mirwaldt.logic.propositional.interpretation.impl.LongInterpretationsIterable;
import net.mirwaldt.logic.propositional.normalizer.api.PropositionNormalizer;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import net.mirwaldt.logic.propositional.proposition.impl.OrProposition;
import net.mirwaldt.logic.propositional.proposition.impl.VariableProposition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.not;

public class ConjunctivePropositionNormalizer implements PropositionNormalizer {
    @Override
    public Proposition normalize(Proposition proposition) {
        Proposition normalizedProposition = null;
        final List<String> variableNames = new ArrayList<>(proposition.findVariableNames());
        variableNames.sort(Comparator.naturalOrder());
        final LongInterpretationsIterable longInterpretationsIterable = new LongInterpretationsIterable(variableNames);

        for (PropositionInterpretation interpretation : longInterpretationsIterable) {
            if (!proposition.evaluate(interpretation)) {
                final Proposition disjunction = makeDisjunction(variableNames, interpretation);
                if (normalizedProposition == null) {
                    normalizedProposition = disjunction;
                } else {
                    normalizedProposition = normalizedProposition.and(disjunction);
                }
            }
        }

        if (normalizedProposition == null) {
            throw new NoSuchElementException(
                    "No conjunctive normal form available for '" + proposition.toExpression() + "'!");
        } else {
            return normalizedProposition;
        }
    }

    private OrProposition makeDisjunction(List<String> variableNames, PropositionInterpretation interpretation) {
        return OrProposition.create(variableNames.stream()
                .map(VariableProposition::new)
                .map(variableProposition ->
                        (interpretation.get(variableProposition.getVariableName())
                                ? not(variableProposition)
                                : variableProposition)
                )
                .collect(Collectors.toList()));
    }
}
