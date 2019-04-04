package net.mirwaldt.logic.propositional.api;

import java.util.List;

public interface TruthTable {
    List<Interpretation> getInterpretations();
    Proposition getProposition();
    boolean getResult(Interpretation interpretation);
}
