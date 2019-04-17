package net.mirwaldt.logic.propositional.api;

import java.io.Serializable;

@FunctionalInterface
public interface BiBooleanPredicate extends Serializable {
    boolean test(boolean left, boolean right);
}
