package org.smallvaluesofcool.misc.functional.iterators;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;

public class ChainedIterator<T> implements Iterator<T> {
    private Iterator<Iterator<T>> iteratorsIterator;
    private Iterator<T> currentIterator;

    public ChainedIterator(Iterator<T>... iteratorsIterator) {
        this(asList(iteratorsIterator));
    }

    public ChainedIterator(List<Iterator<T>> iteratorCollection) {
        iteratorsIterator = iteratorCollection.iterator();
        if (iteratorsIterator.hasNext()) {
            currentIterator = iteratorsIterator.next();
        }
    }

    @Override
    public boolean hasNext() {
        if (currentIterator == null) {
            return false;
        }
        if (currentIterator.hasNext()) {
            return true;
        } else {
            while (iteratorsIterator.hasNext()) {
                currentIterator = iteratorsIterator.next();
                if (currentIterator.hasNext()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T next() {
        if (hasNext()) {
            return currentIterator.next();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        currentIterator.remove();
    }
}