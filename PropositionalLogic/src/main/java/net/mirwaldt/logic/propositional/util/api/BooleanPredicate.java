package net.mirwaldt.logic.propositional.util.api;

import java.io.Serializable;

@FunctionalInterface
public interface BooleanPredicate extends Serializable {
    boolean test(boolean value);
    
    static BooleanPredicate identity() {
        return (value) -> value;
    }
}
