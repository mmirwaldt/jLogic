package net.mirwaldt.logic.propositional.util;

import net.mirwaldt.logic.propositional.proposition.api.Proposition;

import java.util.Comparator;

public class SingleVariablePropositionComparator implements Comparator<Proposition> {
    @Override
    public int compare(Proposition o1, Proposition o2) {
        return o1.findVariableNames().first().compareTo(o2.findVariableNames().first());
    }
}
