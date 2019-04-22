package net.mirwaldt.logic.propositional.table.impl;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;
import net.mirwaldt.logic.propositional.table.api.TruthTable;

import java.util.*;

public class BitSetTruthTable implements TruthTable {
    private final List<Interpretation> interpretations;
    private final Comparator<Interpretation> comparator;
    private final Proposition proposition;
    private final BitSet resultBits;

    public BitSetTruthTable(List<Interpretation> interpretations,
                            Comparator<Interpretation> comparator,
                            Proposition proposition, BitSet resultBits) {
        checkParameters(interpretations, proposition);
        checkIsStrictMonotonSorted(interpretations, comparator);
        this.interpretations = interpretations;
        this.comparator = comparator;
        this.proposition = proposition;
        this.resultBits = resultBits;
    }

    private static void checkParameters(List<Interpretation> interpretations, Proposition proposition) {
        final Set<String> namesSorted = proposition.findVariableNames();
        if (namesSorted.isEmpty()) {
            throw new IllegalArgumentException(
                    "Proposition has no variables! Proposition: '" + proposition.toExpression() + "'");
        } else if (interpretations.isEmpty()) {
            throw new IllegalArgumentException("Truth table has no interpretations!");
        }
    }
    
    public static void checkIsStrictMonotonSorted(
            List<Interpretation> interpretations, Comparator<Interpretation> comparator) {
        Interpretation previousInterpretation = interpretations.get(0);
        final List<Interpretation> restInterpretations = interpretations.subList(1, interpretations.size());
        for (Interpretation nextInterpretation : restInterpretations) {
            final int comparisonResult = comparator.compare(previousInterpretation, nextInterpretation);
            if(comparisonResult == 0) {
                throw new IllegalArgumentException("Two equal interpretations are forbidden: " 
                        + previousInterpretation + " == " + nextInterpretation);
            } else if(comparisonResult == 1) {
                throw new IllegalArgumentException("Two interpretations in wrong order: "
                        + previousInterpretation + " > " + nextInterpretation);
            }
            previousInterpretation = nextInterpretation;
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
        final int index = Collections.binarySearch(interpretations, interpretation, comparator);
        if (-1 < index) {
            return resultBits.get(index);
        } else {
            throw new NoSuchElementException("Interpretation '" + interpretation + "' not found in truth table.");
        }
    }
}
