package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import net.mirwaldt.logic.propositional.api.TruthTable;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.naturalOrder;
import static net.mirwaldt.logic.propositional.impl.Propositions.*;
import static net.mirwaldt.logic.propositional.util.BitUtils.encode;
import static net.mirwaldt.logic.propositional.util.BitUtils.reverse;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

public class TruthTableGenerator {
    public static TruthTable evaluate(Proposition proposition) {
        final List<String> namesSorted = new ArrayList<>(proposition.findVariableNames());
        namesSorted.sort(naturalOrder());
        checkParameters(proposition, namesSorted);
        
        final int numberOfBits = namesSorted.size();
        final long numberOfRows = 1 << numberOfBits;
        final List<Interpretation> interpretations = new ArrayList<>();
        long resultsAsBits = 0;
        for (long rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            final Interpretation interpretation = new LongInterpretation(namesSorted, reverse(rowIndex, numberOfBits));
            final boolean result = proposition.evaluate(interpretation);
            if(result) {
                resultsAsBits = encode(resultsAsBits, rowIndex, 1);
            }
            interpretations.add(interpretation);
        }
        
        return new LongTruthTable(interpretations, proposition, resultsAsBits);
    }

    private static void checkParameters(Proposition proposition, List<String> namesSorted) {
        if(namesSorted.isEmpty()) {
            throw new IllegalArgumentException(
                    "Proposition has no variables! Proposition: '" + proposition.toExpression() + "'");
        } else if(63 < namesSorted.size()) {
            throw new IllegalArgumentException(
                    "More than 63 variables are not supported yet.");
        }
    }

    public static void main(String[] args) {
        final Proposition A = Propositions.variable("A");
        final Proposition B = Propositions.variable("B");
        final Proposition C = Propositions.variable("C");
        final Proposition D = Propositions.variable("D");

        final Proposition complex = nand(or(A, B),imply(negate(C), D));
        TruthTable truthTable = evaluate(complex);
        System.out.println(
                String.join(" ", truthTable.getInterpretations().get(0).getVariableNames()) 
                + " | "+ truthTable.getProposition().toExpression());
        truthTable.getInterpretations().stream()
                .map(interpretation -> interpretation.asBitsWhitespaceSeparated() 
                        + " | " + toBit(truthTable.getResult(interpretation)))
                .forEach(System.out::println);
    }
}
