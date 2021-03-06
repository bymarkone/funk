package org.javafunk.funk;

import org.javafunk.funk.functors.Mapper;
import org.javafunk.funk.functors.Predicate;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.javafunk.funk.Iterables.materialize;
import static org.javafunk.funk.Literals.iterableWith;
import static org.javafunk.matchbox.Matchers.hasOnlyItemsInOrder;

public class LazilyComprehensionTest {
    @Test
    public void shouldAcceptMapperSourceAndPredicate() {
        // Given
        Mapper<Integer, Integer> mapper = Mappers.identity();
        Iterable<Integer> source = iterableWith(1, 2, 3);
        Predicate<Integer> predicate = Predicates.alwaysTrue();

        // When
        Collection<Integer> result = materialize(Lazily.comprehension(mapper, source, predicate));

        // Then
        assertThat(result, hasOnlyItemsInOrder(1, 2, 3));
    }

    @Test
    public void shouldApplyPredicateToSource() {
        // Given
        Mapper<Integer, Integer> mapper = Mappers.identity();
        Iterable<Integer> source = iterableWith(1, 2, 3);
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer firstInput) {
                return firstInput % 2 != 0;
            }
        };

        // When
        Collection<Integer> result = materialize(Lazily.comprehension(mapper, source, predicate));

        // Then
        assertThat(result, hasOnlyItemsInOrder(1, 3));
    }

    @Test
    public void shouldApplyMapperToPredicatedSource() {
        // Given
        Mapper<Integer, Integer> mapper = new Mapper<Integer, Integer>() {
            @Override
            public Integer map(Integer input) {
                return input * 2;
            }
        };
        Iterable<Integer> source = iterableWith(1, 2, 3);
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer firstInput) {
                return firstInput % 2 != 0;
            }
        };

        // When
        Collection<Integer> result = materialize(Lazily.comprehension(mapper, source, predicate));

        // Then
        assertThat(result, hasOnlyItemsInOrder(2, 6));
    }

    @Test
    public void shouldApplyManyPredicatesToSource() {
        // Given
        Mapper<Integer, Integer> mapper = Mappers.identity();
        Iterable<Integer> source = iterableWith(3, 4, 5, 6, 7, 8, 9);
        Predicate<Integer> oddPredicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer firstInput) {
                return firstInput % 2 != 0;
            }
        };
        Predicate<Integer> divisibleByThreePredicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer firstInput) {
                return firstInput % 3 == 0;
            }
        };

        // When
        Collection<Integer> result = materialize(Lazily.comprehension(mapper, source, oddPredicate, divisibleByThreePredicate));

        // Then
        assertThat(result, hasOnlyItemsInOrder(3, 9));
    }

    @Test
    public void shouldApplyManyPredicatesAndMapSource() {
        // Given
        Mapper<Integer, String> toUpperMapper = new Mapper<Integer, String>() {
            @Override
            public String map(Integer input) {
                return input.toString() + ": Looks good";
            }
        };
        Iterable<Integer> source = iterableWith(3, 4, 5, 6, 7, 8, 9);
        Predicate<Integer> oddPredicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer firstInput) {
                return firstInput % 2 != 0;
            }
        };
        Predicate<Integer> divisibleByThreePredicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer firstInput) {
                return firstInput % 3 == 0;
            }
        };

        // When
        Collection<String> result = materialize(Lazily.comprehension(toUpperMapper, source, oddPredicate, divisibleByThreePredicate));

        // Then
        assertThat(result, hasOnlyItemsInOrder("3: Looks good", "9: Looks good"));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfSuppliedIterableIsNull() throws Exception {
        // Given
        Mapper<Integer, Integer> mapper = new Mapper<Integer, Integer>() {
            @Override
            public Integer map(Integer input) {
                return input * 2;
            }
        };
        Iterable<Integer> source = null;
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer firstInput) {
                return firstInput % 2 != 0;
            }
        };

        // When
        Lazily.comprehension(mapper, source, predicate);

        // Then a NullPointerException is thrown
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfAnySuppliedPredicateIsNull() throws Exception {
        // Given
        Mapper<Integer, Integer> mapper = new Mapper<Integer, Integer>() {
            @Override
            public Integer map(Integer input) {
                return input * 2;
            }
        };
        Iterable<Integer> source = null;
        Predicate<Integer> firstPredicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer firstInput) {
                return firstInput % 2 != 0;
            }
        };
        Predicate<Integer> secondPredicate = null;
        // When
        Lazily.comprehension(mapper, source, firstPredicate, secondPredicate);

        // Then a NullPointerException is thrown
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerIfSuppliedMapperIsNull() {
        // Given
        Mapper<String, String> mapper = null;
        Iterable<String> inputs = iterableWith("ac", "ab", "bc", "abc", "bcd", "bad");
        Predicate<String> nonNullPredicate = Predicates.alwaysTrue();

        // When
        Lazily.comprehension(mapper, inputs, nonNullPredicate);

        // Then a NullPointerException is thrown.
    }

}
