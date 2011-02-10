package org.smallvaluesofcool.misc.collections;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;

public class HashBagTest {
    @Test
    public void shouldReturnEmpty() {
        // Given
        Bag<Integer> bag = new HashBag<Integer>();

        // When
        boolean empty = bag.isEmpty();

        // Then
        assertThat(empty, is(true));
    }

    @Test
    public void shouldNotReturnEmptyOncePopulated() {
        // Given
        Bag<Integer> bag = new HashBag<Integer>();
        bag.add(1);

        // When
        boolean empty = bag.isEmpty();

        // Then
        assertThat(empty, is(false));
    }

    @Test
    public void shouldContainAddedItems() {
        // Given
        Bag<Integer> bag = new HashBag<Integer>();
        bag.add(1);

        // When
        boolean contains = bag.contains(1);

        // Then
        assertThat(contains, is(true));
    }

    @Test
    public void shouldNotContainOtherItems() {
        // Given
        Bag<Integer> bag = new HashBag<Integer>();
        bag.add(1);

        // When
        boolean contains = bag.contains(2);

        // Then
        assertThat(contains, is(false));
    }

    @Test
    public void shouldContainAllAddedItems() {
        // Given
        Bag<Integer> bag = new HashBag<Integer>();

        // When
        bag.addAll(asList(1, 2));

        // Then
        assertThat(bag.contains(1), is(true));
        assertThat(bag.contains(2), is(true));
        assertThat(bag.contains(3), is(false));
    }

    @Test
    public void shouldHaveZeroSize() {
        // Given
        Bag<Integer> bag = new HashBag<Integer>();

        // When

        // Then
        assertThat(bag.size(), is(0));
    }

    @Test
    public void shouldHaveSize() {
        // Given
        Bag<Integer> bag = new HashBag<Integer>();

        // When
        bag.addAll(asList(1, 2, 3));

        // Then
        assertThat(bag.size(), is(3));
    }

    @Test
    public void shouldIterate() {
        // Given
        Bag<Integer> bag = new HashBag<Integer>();
        bag.addAll(asList(1, 2, 2, 3));

        // When
        List<Integer> actual = IteratorUtils.toList(bag.iterator());

        // Then
        assertThat(actual, Matchers.containsInAnyOrder(1, 2, 2, 3));
    }

    @Test
    public void shouldRemoveItems() {
        // Given
        Bag<Integer> bag = new HashBag<Integer>();
        bag.addAll(asList(1, 2, 3));

        // When
        bag.remove(2);

        // Then
        assertThat(bag, Matchers.containsInAnyOrder(1, 3));
    }

    @Test
    public void shouldRemoveAllItems() {
        // Given
        Bag<Integer> bag = new HashBag<Integer>();
        bag.addAll(asList(1, 2, 3, 2, 3, 4, 4));

        // When
        bag.removeAll(asList(2, 2, 3));

        // Then
        assertThat(bag, Matchers.containsInAnyOrder(1, 3, 4, 4));
    }

    @Test
    public void shouldClearBag() {
        // Given
        Bag<Integer> bag = new HashBag<Integer>();
        bag.addAll(asList(1, 2, 3, 2, 3, 4, 4));

        // When
        bag.clear();

        // Then
        assertThat(bag.size(), is(0));
    }
}