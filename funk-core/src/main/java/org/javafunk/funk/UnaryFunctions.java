/*
 * Copyright (C) 2011-Present Funk committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.funk;

import org.javafunk.funk.functors.functions.UnaryFunction;

public class UnaryFunctions {
    private UnaryFunctions() {}

    public static <T> UnaryFunction<T, T> identity() {
        return new UnaryFunction<T, T>() {
            @Override public T call(T input) {
                return input;
            }
        };
    }

    public static <Q, R, S> UnaryFunction<Q, S> chain(
            final UnaryFunction<Q, R> first,
            final UnaryFunction<R, S> second) {
        return new UnaryFunction<Q, S>() {
            @Override public S call(Q input) {
                return second.call(first.call(input));
            }
        };
    }

    public static <Q, R, S, T> UnaryFunction<Q, T> chain(
            final UnaryFunction<Q, R> first,
            final UnaryFunction<R, S> second,
            final UnaryFunction<S, T> third) {
        return new UnaryFunction<Q, T>() {
            @Override public T call(Q input) {
                return third.call(second.call(first.call(input)));
            }
        };
    }

    public static <Q, R, S, T, U> UnaryFunction<Q, U> chain(
            final UnaryFunction<Q, R> first,
            final UnaryFunction<R, S> second,
            final UnaryFunction<S, T> third,
            final UnaryFunction<T, U> fourth) {
        return new UnaryFunction<Q, U>() {
            @Override public U call(Q input) {
                return fourth.call(third.call(second.call(first.call(input))));
            }
        };
    }

    public static <Q, R, S, T, U, V> UnaryFunction<Q, V> chain(
            final UnaryFunction<Q, R> first,
            final UnaryFunction<R, S> second,
            final UnaryFunction<S, T> third,
            final UnaryFunction<T, U> fourth,
            final UnaryFunction<U, V> fifth) {
        return new UnaryFunction<Q, V>() {
            @Override public V call(Q input) {
                return fifth.call(fourth.call(third.call(second.call(first.call(input)))));
            }
        };
    }

    public static <Q, R, S, T, U, V, W> UnaryFunction<Q, W> chain(
            final UnaryFunction<Q, R> first,
            final UnaryFunction<R, S> second,
            final UnaryFunction<S, T> third,
            final UnaryFunction<T, U> fourth,
            final UnaryFunction<U, V> fifth,
            final UnaryFunction<V, W> sixth) {
        return new UnaryFunction<Q, W>() {
            @Override public W call(Q input) {
                return sixth.call(fifth.call(fourth.call(third.call(second.call(first.call(input))))));
            }
        };
    }

    public static <Q, R, S, T, U, V, W, X> UnaryFunction<Q, X> chain(
            final UnaryFunction<Q, R> first,
            final UnaryFunction<R, S> second,
            final UnaryFunction<S, T> third,
            final UnaryFunction<T, U> fourth,
            final UnaryFunction<U, V> fifth,
            final UnaryFunction<V, W> sixth,
            final UnaryFunction<W, X> seventh) {
        return new UnaryFunction<Q, X>() {
            @Override public X call(Q input) {
                return seventh.call(sixth.call(fifth.call(fourth.call(third.call(second.call(first.call(input)))))));
            }
        };
    }

    public static <Q, R, S, T, U, V, W, X, Y> UnaryFunction<Q, Y> chain(
            final UnaryFunction<Q, R> first,
            final UnaryFunction<R, S> second,
            final UnaryFunction<S, T> third,
            final UnaryFunction<T, U> fourth,
            final UnaryFunction<U, V> fifth,
            final UnaryFunction<V, W> sixth,
            final UnaryFunction<W, X> seventh,
            final UnaryFunction<X, Y> eighth) {
        return new UnaryFunction<Q, Y>() {
            @Override public Y call(Q input) {
                return eighth.call(seventh.call(sixth.call(fifth.call(fourth.call(third.call(second.call(first.call(input))))))));
            }
        };
    }

    public static <Q, R, S, T, U, V, W, X, Y, Z> UnaryFunction<Q, Z> chain(
            final UnaryFunction<Q, R> first,
            final UnaryFunction<R, S> second,
            final UnaryFunction<S, T> third,
            final UnaryFunction<T, U> fourth,
            final UnaryFunction<U, V> fifth,
            final UnaryFunction<V, W> sixth,
            final UnaryFunction<W, X> seventh,
            final UnaryFunction<X, Y> eighth,
            final UnaryFunction<Y, Z> ninth) {
        return new UnaryFunction<Q, Z>() {
            @Override public Z call(Q input) {
                return ninth.call(eighth.call(seventh.call(sixth.call(fifth.call(fourth.call(third.call(second.call(first.call(input)))))))));
            }
        };
    }
}
