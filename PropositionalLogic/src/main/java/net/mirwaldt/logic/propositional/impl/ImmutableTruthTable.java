package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.TruthTable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;

public class ImmutableTruthTable implements TruthTable {
    private final List<String> variableNames;
    private final Map<List<Integer>, Integer> table;

    public ImmutableTruthTable(List<String> variableNames, Map<List<Integer>, Integer> table) {
        this.variableNames = unmodifiableList(variableNames);
        this.table = unmodifiableMap(table);
    }

    @Override
    public List<String> getVariableNames() {
        return variableNames;
    }

    @Override
    public Map<List<Integer>, Integer> getTable() {
        return table;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableTruthTable that = (ImmutableTruthTable) o;
        return Objects.equals(variableNames, that.variableNames) &&
                Objects.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableNames, table);
    }
}
