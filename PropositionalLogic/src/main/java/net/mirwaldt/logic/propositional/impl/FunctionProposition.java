package net.mirwaldt.logic.propositional.impl;

import net.mirwaldt.logic.propositional.api.Interpretation;
import net.mirwaldt.logic.propositional.api.Proposition;
import net.mirwaldt.logic.propositional.util.PropositionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBits;

public class FunctionProposition implements Proposition {
    private final String functionName;
    private final List<VariableProposition> parameters;
    private final Map<List<Boolean>, Boolean> functionValues;

    public FunctionProposition(String functionName, 
                               List<VariableProposition> parameters, 
                               Map<List<Boolean>, Boolean> functionValues) {
        this.functionName = functionName;
        this.parameters = parameters;
        this.functionValues = functionValues;
        
        checkParameters(functionName, parameters, functionValues);
    }

    private void checkParameters(String functionName, List<VariableProposition> parameters, 
                                 Map<List<Boolean>, Boolean> functionValues) {
        Objects.requireNonNull(functionName);
        Objects.requireNonNull(parameters);
        Objects.requireNonNull(functionValues);
        
        if(parameters.isEmpty()) {
            throw new IllegalArgumentException("Function " + functionName + " must have at least one parameter.");
        }
        
        if(functionValues.isEmpty()) {
            throw new IllegalArgumentException("Function " + functionName + " must have at least one function value.");
        }

        final int variableCount = parameters.size();
        for (List<Boolean> row : functionValues.keySet()) {
            if(row.size() != variableCount) {
                throw new IllegalArgumentException("One row in the function values for function " + functionName
                        + " has more/fewer arguments than list of variables: row=" 
                        + toBits(row) + ", variables=" + createParameterListString());
            }
        }
    }

    @Override
    public boolean evaluate(Interpretation interpretation) {
        final List<Boolean> arguments = parameters.stream()
                .map(proposition -> proposition.evaluate(interpretation))
                .collect(Collectors.toList());
        Boolean functionValue = functionValues.get(arguments);
        if (functionValue == null) {
            throw new NoSuchElementException(toExpression() + " not defined for "
                    + functionName + "(" +
                    arguments.stream()
                            .map(PropositionUtils::toBit)
                            .map(String::valueOf)
                            .collect(joining(","))
                    + ")");
        } else {
            return functionValue;
        }
    }

    @Override
    public String toExpression() {
        final String parameterList = createParameterListString();
        return functionName + "(" + parameterList + ")";
    }

    @Override
    public Set<String> findVariableNames() {
        return parameters.stream().map(VariableProposition::getVariableName).collect(toSet());
    }

    private String createParameterListString() {
        return parameters.stream()
                .map(VariableProposition::getVariableName)
                .collect(joining(","));
    }
}
