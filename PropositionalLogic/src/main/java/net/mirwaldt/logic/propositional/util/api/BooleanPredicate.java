package net.mirwaldt.logic.propositional.util.api;

@FunctionalInterface
public interface BooleanPredicate {
    boolean test(boolean value);
    
    static BooleanPredicate identity() {
        return (value) -> value;
    }
}
