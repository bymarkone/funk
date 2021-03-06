package org.javafunk.funk.matchers.implementations;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.javafunk.funk.monads.Either;

public class EitherAnyLeftMatcher<S, T> extends TypeSafeDiagnosingMatcher<Either<S, T>> {
    @Override public void describeTo(Description description) {
        description.appendText("Either to be Left.");
    }

    @Override protected boolean matchesSafely(Either<S, T> item, Description mismatchDescription) {
        mismatchDescription.appendText("got Right.");
        return item.isLeft();
    }

    @Override public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
