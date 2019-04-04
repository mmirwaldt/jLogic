package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import net.mirwaldt.logic.propositional.api.TruthTable;

import java.util.List;
import java.util.NoSuchElementException;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;

public class LongTruthTable implements TruthTable {
    private final List<Interpretation> interpretations;
    private final Proposition proposition;
    private final long resultBits;
    
    public LongTruthTable(List<Interpretation> interpretations,
                          Proposition proposition, long resultBits) {
        this.interpretations = interpretations;
        this.proposition = proposition;
        this.resultBits = resultBits;
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
        if(-1 < index) {
            long bitMask = 1L << index;
            int bit = (int) ((resultBits & bitMask) >> index);
            return fromBit(bit);
        } else {
            throw new NoSuchElementException("Interpretation '" + interpretation + "' not found in truth table.");
        }
    }
}
