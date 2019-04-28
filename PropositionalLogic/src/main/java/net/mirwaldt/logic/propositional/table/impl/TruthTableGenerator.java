package net.mirwaldt.logic.propositional.table.impl;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;
import net.mirwaldt.logic.propositional.interpretation.impl.LongInterpretationsIterable;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static net.mirwaldt.logic.propositional.proposition.api.Proposition.not;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

public class TruthTableGenerator {
    public static void main(String[] args) {
        final Proposition A = Proposition.variable("A");
        final Proposition B = Proposition.variable("B");
        final Proposition C = Proposition.variable("C");
        final Proposition D = Proposition.variable("D");

        final Proposition complex = (A.or(B)).nand(not(C).imply(D));
        
        final List<String> variableNames = new ArrayList<>(complex.findVariableNames());
        variableNames.sort(Comparator.naturalOrder());
        final LongInterpretationsIterable longInterpretationsIterable = new LongInterpretationsIterable(variableNames);

        System.out.println(
                String.join(" ", variableNames) + " | " + complex.toExpression());
        for (Interpretation interpretation : longInterpretationsIterable) {
            System.out.println(interpretation.asBitsWhitespaceSeparated() + " | " + toBit(complex.evaluate(interpretation)));
        }

        System.out.println("DNF: " + complex.toDNF().toExpression());
        System.out.println("KNF: " + complex.toKNF().toExpression());
    }
}
