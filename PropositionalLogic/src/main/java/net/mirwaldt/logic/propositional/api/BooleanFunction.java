package net.mirwaldt.logic.propositional.api;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

public interface BooleanFunction {
    String getFunctionName();

    List<String> getParameterNames();

    List<Interpretation> getInterpretations();

    boolean getResult(Interpretation interpretation);

    default String getFunctionSignature() {
        return getFunctionName() + "(" + String.join(",", getParameterNames()) + ")";
    }

    default String getFunctionCall(Interpretation interpretation) {
        return getFunctionName() + "("
                + getParameterNames().stream()
                .map(parameterName -> String.valueOf(toBit(interpretation.get(parameterName))))
                .collect(joining(",")) + ")";
    }
}
