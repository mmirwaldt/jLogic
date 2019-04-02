package net.mirwaldt.logic.propositional.util;

import net.mirwaldt.logic.propositional.api.Proposition;
import net.mirwaldt.logic.propositional.impl.BinaryProposition;

public class PropositionUtils {
    public static String toFinalExpression(Proposition proposition) {
        if(proposition instanceof BinaryProposition) {
            return "(" + proposition.toExpression() + ")";
        } else {
            return proposition.toExpression();
        }
    }
}
