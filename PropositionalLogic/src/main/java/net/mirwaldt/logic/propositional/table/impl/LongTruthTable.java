package net.mirwaldt.logic.propositional.table.impl;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import net.mirwaldt.logic.propositional.table.api.TruthTable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static net.mirwaldt.logic.propositional.util.BitUtils.decode;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;

public class LongTruthTable implements TruthTable {
    private final List<Interpretation> interpretations;
    private final Proposition proposition;
    private final long resultBits;

    public LongTruthTable(List<Interpretation> interpretations,
                          Proposition proposition, long resultBits) {
        checkParameters(interpretations, proposition);
        this.interpretations = interpretations;
        this.proposition = proposition;
        this.resultBits = resultBits;
    }

    private static void checkParameters(List<Interpretation> interpretations, Proposition proposition) {
        final Set<String> namesSorted = proposition.findVariableNames();
        if (namesSorted.isEmpty()) {
            throw new IllegalArgumentException(
                    "Proposition has no variables! Proposition: '" + proposition.toExpression() + "'");
        } else if (63 < namesSorted.size()) {
            throw new IllegalArgumentException("More than 63 variables are not supported.");
        } else if (interpretations.isEmpty()) {
            throw new IllegalArgumentException("Truth table has no interpretations!");
        } else if (63 < interpretations.size()) {
            throw new IllegalArgumentException("More than 63 interpretations are not supported.");
        }
    }

    @Override
    public List<Interpretation> getInterpretations() {
        return interpretations;
    }

    @Override
    public Proposition getProposition() {
        return proposition;
    }

    @Override
    public boolean getResult(Interpretation interpretation) {
        final int index = interpretations.indexOf(interpretation);
        if (-1 < index) {
            return fromBit(decode(resultBits, index));
        } else {
            throw new NoSuchElementException("Interpretation '" + interpretation + "' not found in truth table.");
        }
    }
}
