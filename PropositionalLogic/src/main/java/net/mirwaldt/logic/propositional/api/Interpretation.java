package net.mirwaldt.logic.propositional.api;

import java.util.List;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.toBit;

/**
 * maps every variable to a single boolean value.
 */
public interface Interpretation {
    /**
     * get the boolean value (i.e. the interpretation) for a variable
     * @param variableName the name of the variable
     * @return the boolean value (i.e. the interpretation) of the variable 
     */
   boolean get(String variableName);
   
   default int getAsBit(String variableName) {
       return toBit(get(variableName));
   }
   
   List<String> getVariableNames();
}
