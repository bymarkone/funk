package org.javafunk.funk.predicates;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.javafunk.funk.functors.Predicate;

public class TruePredicate<T> extends Predicate<T> {
    @Override
    public boolean evaluate(T item) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
