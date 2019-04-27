package net.mirwaldt.logic.propositional.util;

import net.mirwaldt.logic.propositional.proposition.api.MultiProposition;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;

public class PropositionUtils {
    public static String toFinalExpression(Proposition proposition) {
        if (proposition instanceof MultiProposition) {
            return "(" + proposition.toExpression() + ")";
        } else {
            return proposition.toExpression();
        }
    }

    public static int toBit(boolean value) {
        return (value) ? 1 : 0;
    }

    public static boolean fromBit(int bit) {
        if (bit == 0) {
            return false;
        } else if (bit == 1) {
            return true;
        } else {
            throw new IllegalArgumentException(bit + " is not a bit. A bit can only have the value either 0 or 1.");
        }
    }
}
