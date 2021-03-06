package net.mirwaldt.logic.propositional.proposition.api;

import net.mirwaldt.logic.propositional.interpretation.api.PropositionInterpretation;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

public interface BooleanFunction {
    String getFunctionName();

    List<String> getParameterNames();

    List<PropositionInterpretation> getInterpretations();

    boolean getResult(PropositionInterpretation interpretation);

    default String getFunctionSignature() {
        return getFunctionName() + "(" + String.join(",", getParameterNames()) + ")";
    }

    default String getFunctionCall(PropositionInterpretation interpretation) {
        return getFunctionName() + "("
                + getParameterNames().stream()
                .map(parameterName -> String.valueOf(toBit(interpretation.get(parameterName))))
                .collect(joining(",")) + ")";
    }
}
