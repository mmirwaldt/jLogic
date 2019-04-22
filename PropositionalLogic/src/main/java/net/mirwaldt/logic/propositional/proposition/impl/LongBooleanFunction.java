package net.mirwaldt.logic.propositional.proposition.impl;

import net.mirwaldt.logic.propositional.proposition.api.BooleanFunction;
import net.mirwaldt.logic.propositional.interpretation.api.Interpretation;

import java.util.List;
import java.util.NoSuchElementException;

import static net.mirwaldt.logic.propositional.util.BitUtils.decode;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;

public class LongBooleanFunction implements BooleanFunction {
    private final String functionName;
    private final List<String> parameterNames;
    private final List<Interpretation> interpretations;
    private final long resultBits;

    public LongBooleanFunction(String functionName, List<String> parameterNames, 
                               List<Interpretation> interpretations, long resultBits) {
        this.functionName = functionName;
        this.parameterNames = parameterNames;
        this.interpretations = interpretations;
        this.resultBits = resultBits;
    }

    @Override
    public String getFunctionName() {
        return functionName;
    }

    @Override
    public List<String> getParameterNames() {
        return parameterNames;
    }

    @Override
    public List<Interpretation> getInterpretations() {
        return interpretations;
    }

    @Override
    public boolean getResult(Interpretation interpretation) {
        final int index = interpretations.indexOf(interpretation);
        if(-1 < index) {
            return fromBit(decode(resultBits, index));
        } else {
            throw new NoSuchElementException("Function not defined for " + getFunctionCall(interpretation) + ".");
        }
    }
}
