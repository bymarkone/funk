/*
 * Copyright (C) 2011-Present Funk committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.funk.functors;

import org.javafunk.funk.functors.functions.BinaryFunction;

public abstract class Reducer<S, T> implements BinaryFunction<T, S, T> {
    public abstract T accumulate(T accumulator, S element);

    @Override public T call(T accumulator, S element) {
        return accumulate(accumulator, element);
    }
}
