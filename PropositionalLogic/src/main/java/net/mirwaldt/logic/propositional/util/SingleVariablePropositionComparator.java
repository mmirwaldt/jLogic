package net.mirwaldt.logic.propositional.util;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;

import java.util.Comparator;

public class SingleVariablePropositionComparator implements Comparator<Proposition> {
    @Override
    public int compare(Proposition left, Proposition right) {
        String leftVarName = left.findVariableNames().first();
        String rightVarName = right.findVariableNames().first();
        if(leftVarName.equals(rightVarName)) {
            if(left.equals(right)) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return leftVarName.compareTo(rightVarName);
        }
    }
}
