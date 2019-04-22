package net.mirwaldt.logic.propositional.util;

import net.mirwaldt.logic.propositional.proposition.api.MultiProposition;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static List<Integer> toBits(List<Boolean> arguments) {
        return arguments.stream()
                .map(PropositionUtils::toBit)
                .collect(Collectors.toList());
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

    public static List<Boolean> fromBits(Integer...bits) {
        return Stream.of(bits)
                .map(PropositionUtils::fromBit)
                .collect(Collectors.toList());
    }
}
