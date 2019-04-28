package net.mirwaldt.logic.propositional.normalizer.impl;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;
import net.mirwaldt.logic.propositional.interpretation.impl.LongInterpretationsIterable;
import net.mirwaldt.logic.propositional.normalizer.api.PropositionNormalizer;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import net.mirwaldt.logic.propositional.proposition.impl.AndProposition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class LongDisjunctivePropositionNormalizer implements PropositionNormalizer {
    @Override
    public Proposition normalize(Proposition proposition) {
        Proposition normalizedProposition = null;
        final List<String> variableNames = new ArrayList<>(proposition.findVariableNames());
        variableNames.sort(Comparator.naturalOrder());
        final LongInterpretationsIterable longInterpretationsIterable = new LongInterpretationsIterable(variableNames);
        
        for (Interpretation interpretation : longInterpretationsIterable) {
            if(proposition.evaluate(interpretation)) {
                final Proposition conjunction = interpretation.join(AndProposition::create);
                if(normalizedProposition == null) {
                    normalizedProposition = conjunction;
                } else {
                    normalizedProposition = normalizedProposition.or(conjunction);
                }
            }
        }
        
        if(normalizedProposition == null) {
            throw new NoSuchElementException(
                    "No disjunctive normal form available for '" + proposition.toExpression() + "'!");
        } else {
            return normalizedProposition;
        }
    }
}
