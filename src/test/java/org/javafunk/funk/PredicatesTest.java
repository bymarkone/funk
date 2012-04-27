package org.javafunk.funk;

import org.javafunk.funk.functors.Predicate;
import org.javafunk.funk.predicates.FalsePredicate;
import org.javafunk.funk.predicates.NotPredicate;
import org.javafunk.funk.predicates.TruePredicate;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.javafunk.funk.Predicates.alwaysFalse;
import static org.javafunk.funk.Predicates.alwaysTrue;
import static org.javafunk.funk.Predicates.not;

public class PredicatesTest {
    @Test
    public void shouldReturnNotPredicateOverSuppliedPredicate() throws Exception {
        // Given
        Predicate<String> predicate = new FalsePredicate<String>();
        Predicate<String> expected = new NotPredicate<String>(predicate);

        // When
        Predicate<String> actual = not(predicate);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnTruePredicate() throws Exception {
        // Given
        Predicate<String> expected = new TruePredicate<String>();

        // When
        Predicate<String> actual = alwaysTrue();

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnFalsePredicate() throws Exception {
        // Given
        Predicate<String> expected = new FalsePredicate<String>();

        // When
        Predicate<String> actual = alwaysFalse();

        // Then
        assertThat(actual, is(expected));
    }
}
