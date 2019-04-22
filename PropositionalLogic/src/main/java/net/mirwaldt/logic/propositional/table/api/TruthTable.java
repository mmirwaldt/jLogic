package net.mirwaldt.logic.propositional.table.api;

import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;
import net.mirwaldt.logic.propositional.proposition.api.Proposition;

import java.util.List;

public interface TruthTable {
    List<Interpretation> getInterpretations();
    Proposition getProposition();
    boolean getResult(Interpretation interpretation);
}
