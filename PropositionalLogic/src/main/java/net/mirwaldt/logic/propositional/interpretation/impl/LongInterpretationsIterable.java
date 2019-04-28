package net.mirwaldt.logic.propositional.interpretation.impl;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;

import java.util.Iterator;
import java.util.List;

import static net.mirwaldt.logic.propositional.util.BitUtils.reverseBits;

public class LongInterpretationsIterable implements Iterable<Interpretation> {
    private final List<String> variableNames;

    public LongInterpretationsIterable(List<String> variableNames) {
        this.variableNames = variableNames;
    }

    @Override
    public Iterator<Interpretation> iterator() {
        return new Iterator<>() {
            final int numberOfBits = variableNames.size();
            final long numberOfRows = 1 << numberOfBits;
            long rowIndex = 0;

            @Override
            public boolean hasNext() {
                return rowIndex < numberOfRows;
            }

            @Override
            public Interpretation next() {
                return new LongInterpretation(variableNames, reverseBits(rowIndex++, numberOfBits));
            }
        };
    }
}
