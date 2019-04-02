package net.mirwaldt.logic.propositional.api;

/**
 * maps every variable to a single boolean value.
 */
@FunctionalInterface
public interface Interpretation {
    /**
     * get the boolean value (i.e. the interpretation) of the variable
     * @param variableName the name of the variable
     * @return
     */
   boolean get(String variableName); 
}
