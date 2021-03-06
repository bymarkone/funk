/*
 * Copyright (C) 2011-Present Funk committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.funk;

import org.javafunk.funk.functors.Equivalence;
import org.javafunk.funk.functors.predicates.BinaryPredicate;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.javafunk.funk.Literals.iterableWith;

public class LazilyEquateTest {
    @Test
    public void shouldReturnAnIterableContainingTheResultOfEquatingEachElementInTheSuppliedIterables()
            throws Exception {
        // Given
        Iterable<String> first = iterableWith("Dog", "Cat", "Goldfish");
        Iterable<String> second = iterableWith("DOG", "BAT", "GOLDFISH");

        // When
        Iterable<Boolean> equateResultIterable = Lazily.equate(first, second, new Equivalence<String>() {
            public boolean equal(String first, String second) {
                return first.compareToIgnoreCase(second) == 0;
            }
        });
        Iterator<Boolean> equateResultIterator = equateResultIterable.iterator();

        // Then
        assertThat(equateResultIterator.hasNext(), is(true));
        assertThat(equateResultIterator.next(), is(true));
        assertThat(equateResultIterator.hasNext(), is(true));
        assertThat(equateResultIterator.next(), is(false));
        assertThat(equateResultIterator.hasNext(), is(true));
        assertThat(equateResultIterator.next(), is(true));
        assertThat(equateResultIterator.hasNext(), is(false));
    }

    @Test
    public void shouldOnlyEquateAsManyElementsAsPossibleIfTheFirstIterableIsShorterThanTheSecond() throws Exception {
        // Given
        Iterable<String> first = iterableWith("Dog", "Cat", "Goldfish");
        Iterable<String> second = iterableWith("DOG", "BAT", "GOLDFISH", "HORSE", "PIG");

        // When
        Iterable<Boolean> equateResultIterable = Lazily.equate(first, second, new Equivalence<String>() {
            public boolean equal(String first, String second) {
                return first.compareToIgnoreCase(second) == 0;
            }
        });
        Iterator<Boolean> equateResultIterator = equateResultIterable.iterator();

        // Then
        assertThat(equateResultIterator.hasNext(), is(true));
        assertThat(equateResultIterator.next(), is(true));
        assertThat(equateResultIterator.hasNext(), is(true));
        assertThat(equateResultIterator.next(), is(false));
        assertThat(equateResultIterator.hasNext(), is(true));
        assertThat(equateResultIterator.next(), is(true));
        assertThat(equateResultIterator.hasNext(), is(false));
    }

    @Test
    public void shouldOnlyEquateAsManyElementsAsPossibleIfTheSecondIterableIsShorterThanTheFirst() throws Exception {
        // Given
        Iterable<String> first = iterableWith("Dog", "Cat", "Goldfish", "Horse", "Pig");
        Iterable<String> second = iterableWith("DOG", "BAT", "GOLDFISH");

        // When
        Iterable<Boolean> equateResultIterable = Lazily.equate(first, second, new Equivalence<String>() {
            public boolean equal(String first, String second) {
                return first.compareToIgnoreCase(second) == 0;
            }
        });
        Iterator<Boolean> equateResultIterator = equateResultIterable.iterator();

        // Then
        assertThat(equateResultIterator.hasNext(), is(true));
        assertThat(equateResultIterator.next(), is(true));
        assertThat(equateResultIterator.hasNext(), is(true));
        assertThat(equateResultIterator.next(), is(false));
        assertThat(equateResultIterator.hasNext(), is(true));
        assertThat(equateResultIterator.next(), is(true));
        assertThat(equateResultIterator.hasNext(), is(false));
    }

    @Test
    public void shouldAllowIteratorToBeCalledMultipleTimesReturningDifferentIterators() throws Exception {
        // Given
        Iterable<String> first = iterableWith("Dog", "Cat", "Goldfish");
        Iterable<String> second = iterableWith("DOG", "BAT", "GOLDFISH");

        // When
        Iterable<Boolean> iterable = Lazily.equate(first, second, new Equivalence<String>() {
            public boolean equal(String first, String second) {
                return first.compareToIgnoreCase(second) == 0;
            }
        });
        Iterator<Boolean> iterator1 = iterable.iterator();
        Iterator<Boolean> iterator2 = iterable.iterator();

        // Then
        assertThat(iterator1.next(), is(true));
        assertThat(iterator1.next(), is(false));
        assertThat(iterator2.next(), is(true));
        assertThat(iterator1.next(), is(true));
        assertThat(iterator2.next(), is(false));
        assertThat(iterator2.next(), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfEquivalencePassedToEquateIsNull() throws Exception {
        // Given
        Iterable<String> first = iterableWith("Dog", "Cat", "Goldfish");
        Iterable<String> second = iterableWith("DOG", "BAT", "GOLDFISH");
        Equivalence<? super String> equivalence = null;

        // When
        Lazily.equate(first, second, equivalence);

        // Then a NullPointerException is thrown.
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfBinaryPredicatePassedToEquateIsNull() throws Exception {
        // Given
        Iterable<String> first = iterableWith("Dog", "Cat", "Goldfish");
        Iterable<String> second = iterableWith("DOG", "BAT", "GOLDFISH");
        BinaryPredicate<? super String, ? super String> equivalence = null;

        // When
        Lazily.equate(first, second, equivalence);

        // Then a NullPointerException is thrown.
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfFirstIterablePassedToEquivalenceEquateIsNull() throws Exception {
        // Given
        Iterable<String> first = null;
        Iterable<String> second = iterableWith("DOG", "BAT", "GOLDFISH");
        Equivalence<? super String> equivalence = new Equivalence<String>() {
            public boolean equal(String first, String second) {
                return first.compareToIgnoreCase(second) == 0;
            }
        };

        // When
        Lazily.equate(first, second, equivalence);

        // Then a NullPointerException is thrown.
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfFirstIterablePassedToBinaryPredicateEquateIsNull() throws Exception {
        // Given
        Iterable<String> first = null;
        Iterable<String> second = iterableWith("DOG", "BAT", "GOLDFISH");
        BinaryPredicate<? super String, ? super String> equivalence = new BinaryPredicate<String, String>() {
            @Override public boolean evaluate(String first, String second) {
                return first.compareToIgnoreCase(second) == 0;
            }
        };

        // When
        Lazily.equate(first, second, equivalence);

        // Then a NullPointerException is thrown.
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfSecondIterablePassedToEquivalenceEquateIsNull() throws Exception {
        // Given
        Iterable<String> first = iterableWith("Dog", "Cat", "Goldfish");
        Iterable<String> second = null;
        Equivalence<? super String> equivalence = new Equivalence<String>() {
            public boolean equal(String first, String second) {
                return first.compareToIgnoreCase(second) == 0;
            }
        };

        // When
        Lazily.equate(first, second, equivalence);

        // Then a NullPointerException is thrown.
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfSecondIterablePassedToBinaryPredicateEquateIsNull() throws Exception {
        // Given
        Iterable<String> first = iterableWith("Dog", "Cat", "Goldfish");
        Iterable<String> second = null;
        BinaryPredicate<? super String, ? super String> equivalence = new BinaryPredicate<String, String>() {
            @Override public boolean evaluate(String first, String second) {
                return first.compareToIgnoreCase(second) == 0;
            }
        };

        // When
        Lazily.equate(first, second, equivalence);

        // Then a NullPointerException is thrown.
    }
}
