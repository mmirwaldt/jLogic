package net.mirwaldt.logic.propositional.proposition.api;

@FunctionalInterface
public interface BiBooleanPredicate {
    boolean test(boolean left, boolean right);
}
