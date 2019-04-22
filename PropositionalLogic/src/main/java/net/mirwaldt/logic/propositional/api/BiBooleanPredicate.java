package net.mirwaldt.logic.propositional.api;

@FunctionalInterface
public interface BiBooleanPredicate {
    boolean test(boolean left, boolean right);
}
