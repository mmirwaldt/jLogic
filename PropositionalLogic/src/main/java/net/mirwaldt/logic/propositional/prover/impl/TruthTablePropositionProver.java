package net.mirwaldt.logic.propositional.prover.impl;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;
import net.mirwaldt.logic.propositional.interpretation.impl.LongInterpretationsIterable;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import net.mirwaldt.logic.propositional.prover.api.PropositionProver;

import java.util.ArrayList;
import java.util.List;

public class TruthTablePropositionProver implements PropositionProver {
    @Override
    public boolean prove(Proposition proposition) {
        final List<String> variableNames = new ArrayList<>(proposition.findVariableNames());
        final LongInterpretationsIterable longInterpretationsIterable = new LongInterpretationsIterable(variableNames);

        for (PropositionInterpretation interpretation : longInterpretationsIterable) {
            if (!proposition.evaluate(interpretation)) {
                return false;
            }
        }
        return true;
    }
}
