package net.mirwaldt.logic.propositional.util.api;

@FunctionalInterface
public interface BiBooleanPredicate {
    boolean test(boolean left, boolean right);
}
