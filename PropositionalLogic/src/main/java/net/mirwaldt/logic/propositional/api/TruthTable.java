package net.mirwaldt.logic.propositional.api;

import java.util.List;
import java.util.Map;

public interface TruthTable {
    List<String> getVariableNames();
    Map<List<Integer>, Integer> getTable();
}
