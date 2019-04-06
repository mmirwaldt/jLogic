package net.mirwaldt.logic.propositional.util.api;

import java.io.Serializable;

@FunctionalInterface
public interface BiBooleanPredicate extends Serializable {
    boolean test(boolean left, boolean right);
}
