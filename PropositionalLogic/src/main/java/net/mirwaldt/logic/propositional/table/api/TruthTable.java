package net.mirwaldt.logic.propositional.table.api;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;

import java.util.List;

public interface TruthTable {
    List<Interpretation> getInterpretations();
    Proposition getProposition();
    boolean getResult(Interpretation interpretation);
}
