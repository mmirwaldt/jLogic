package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Proposition;
import net.mirwaldt.logic.propositional.api.TruthTable;

import java.util.*;

import static java.util.Comparator.naturalOrder;
import static net.mirwaldt.logic.propositional.impl.ListComparator.listComparator;
import static net.mirwaldt.logic.propositional.impl.Propositions.*;
import static net.mirwaldt.logic.propositional.impl.Propositions.negate;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

public class TruthTableGenerator {
    public static TruthTable evaluate(Proposition proposition) {
        final List<String> namesSorted = new ArrayList<>(proposition.findVariableNames());
        namesSorted.sort(naturalOrder());
        if(namesSorted.isEmpty()) {
            throw new IllegalArgumentException(
                    "Proposition has no variables! Proposition: '" + proposition.toExpression() + "'");
        } else if(63 < namesSorted.size()) {
            throw new IllegalArgumentException(
                    "More than 63 variables are not supported yet.");
        }

        final Map<List<Integer>, Integer> table = new TreeMap<>(listComparator);
        int numberOfBits = namesSorted.size();
        long numberOfRows = 1 << numberOfBits;
        final List<Boolean> rowAsBooleans = new ArrayList<>(numberOfBits);
        final ListInterpretation listInterpretation = new ListInterpretation(namesSorted);
        for (long rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            final List<Integer> rowAsInts = new ArrayList<>(numberOfBits);
            for (int bitIndex = numberOfBits - 1; 0 <= bitIndex; bitIndex--) {
                final long bitMask = 1 << bitIndex;
                final long bit = rowIndex & bitMask;
                if(0 < bit) {
                    rowAsInts.add(1);
                    rowAsBooleans.add(true);
                } else {
                    rowAsInts.add(0);
                    rowAsBooleans.add(false);
                }
            }
            listInterpretation.setValues(rowAsBooleans);
            final boolean result = proposition.evaluate(listInterpretation);
            table.put(rowAsInts, toBit(result));
            rowAsBooleans.clear();
        }
        
        return new ImmutableTruthTable(namesSorted, table);
    }

    public static void main(String[] args) {
        final Proposition A = Propositions.variable("A");
        final Proposition B = Propositions.variable("B");
        final Proposition C = Propositions.variable("C");
        final Proposition D = Propositions.variable("D");

        final Proposition complex = nand(or(A, B),imply(negate(C), D));
        TruthTable truthTable = evaluate(complex);
        System.out.println(truthTable.getVariableNames());
        System.out.println(truthTable.getTable().toString().replace(", [",",\n["));
    }
}
