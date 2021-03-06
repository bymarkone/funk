/*
 * Copyright (C) 2011-Present Funk committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.funk;

import org.javafunk.funk.functors.Mapper;
import org.javafunk.funk.functors.functions.UnaryFunction;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.javafunk.funk.BigDecimals.fromStringToBigDecimal;
import static org.javafunk.funk.BigIntegers.fromFloatToBigInteger;
import static org.javafunk.funk.Doubles.fromIntegerToDouble;
import static org.javafunk.funk.Floats.fromDoubleToFloat;
import static org.javafunk.funk.Integers.fromLongToInteger;
import static org.javafunk.funk.Longs.fromBigDecimalToLong;
import static org.javafunk.funk.Objects.toStringValueFor;

public class UnaryFunctionsTest {
    @Test
    public void composesTwoUnaryFunctions() {
        // Given
        UnaryFunction<String, BigDecimal> first = fromStringToBigDecimal();
        UnaryFunction<BigDecimal, Long> second = fromBigDecimalToLong();

        UnaryFunction<String, Long> chained = UnaryFunctions.compose(first, second);

        // When
        Long result = chained.call("1234");

        // Then
        assertThat(result, is(1234L));
    }

    @Test
    public void composesThreeUnaryFunctions() {
        // Given
        UnaryFunction<String, BigDecimal> first = fromStringToBigDecimal();
        UnaryFunction<BigDecimal, Long> second = fromBigDecimalToLong();
        UnaryFunction<Long, Integer> third = fromLongToInteger();

        UnaryFunction<String, Integer> chained = UnaryFunctions.compose(first, second, third);

        // When
        Integer result = chained.call("1234");

        // Then
        assertThat(result, is(1234));
    }

    @Test
    public void composesFourUnaryFunctions() {
        // Given
        UnaryFunction<String, BigDecimal> first = fromStringToBigDecimal();
        UnaryFunction<BigDecimal, Long> second = fromBigDecimalToLong();
        UnaryFunction<Long, Integer> third = fromLongToInteger();
        UnaryFunction<Integer, Double> fourth = fromIntegerToDouble();

        UnaryFunction<String, Double> chained = UnaryFunctions.compose(first, second, third, fourth);

        // When
        Double result = chained.call("1234");

        // Then
        assertThat(result, is(1234D));
    }

    @Test
    public void composesFiveUnaryFunctions() {
        // Given
        UnaryFunction<String, BigDecimal> first = fromStringToBigDecimal();
        UnaryFunction<BigDecimal, Long> second = fromBigDecimalToLong();
        UnaryFunction<Long, Integer> third = fromLongToInteger();
        UnaryFunction<Integer, Double> fourth = fromIntegerToDouble();
        UnaryFunction<Double, Float> fifth = fromDoubleToFloat();

        UnaryFunction<String, Float> chained = UnaryFunctions.compose(first, second, third, fourth, fifth);

        // When
        Float result = chained.call("1234");

        // Then
        assertThat(result, is(1234F));
    }

    @Test
    public void composesSixUnaryFunctions() {
        // Given
        UnaryFunction<String, BigDecimal> first = fromStringToBigDecimal();
        UnaryFunction<BigDecimal, Long> second = fromBigDecimalToLong();
        UnaryFunction<Long, Integer> third = fromLongToInteger();
        UnaryFunction<Integer, Double> fourth = fromIntegerToDouble();
        UnaryFunction<Double, Float> fifth = fromDoubleToFloat();
        UnaryFunction<Float, BigInteger> sixth = fromFloatToBigInteger();

        UnaryFunction<String, BigInteger> chained = UnaryFunctions.compose(first, second, third, fourth, fifth, sixth);

        // When
        BigInteger result = chained.call("1234");

        // Then
        assertThat(result, is(new BigInteger("1234")));
    }

    @Test
    public void composesSevenUnaryFunctions() {
        // Given
        UnaryFunction<String, BigDecimal> first = fromStringToBigDecimal();
        UnaryFunction<BigDecimal, Long> second = fromBigDecimalToLong();
        UnaryFunction<Long, Integer> third = fromLongToInteger();
        UnaryFunction<Integer, Double> fourth = fromIntegerToDouble();
        UnaryFunction<Double, Float> fifth = fromDoubleToFloat();
        UnaryFunction<Float, BigInteger> sixth = fromFloatToBigInteger();
        UnaryFunction<BigInteger, BigInteger> seventh = toDoubledBigInteger();

        UnaryFunction<String, BigInteger> chained = UnaryFunctions.compose(
                first, second, third, fourth, fifth, sixth, seventh);

        // When
        BigInteger result = chained.call("1234");

        // Then
        assertThat(result, is(new BigInteger("2468")));
    }

    @Test
    public void composesEightUnaryFunctions() {
        // Given
        UnaryFunction<String, BigDecimal> first = fromStringToBigDecimal();
        UnaryFunction<BigDecimal, Long> second = fromBigDecimalToLong();
        UnaryFunction<Long, Integer> third = fromLongToInteger();
        UnaryFunction<Integer, Double> fourth = fromIntegerToDouble();
        UnaryFunction<Double, Float> fifth = fromDoubleToFloat();
        UnaryFunction<Float, BigInteger> sixth = fromFloatToBigInteger();
        UnaryFunction<BigInteger, BigInteger> seventh = toDoubledBigInteger();
        UnaryFunction<BigInteger, String> eighth = toStringValueFor(BigInteger.class);

        UnaryFunction<String, String> chained = UnaryFunctions.compose(
                first, second, third, fourth, fifth, sixth, seventh, eighth);

        // When
        String result = chained.call("1234");

        // Then
        assertThat(result, is("2468"));
    }

    @Test
    public void composesNinthUnaryFunctions() {
        // Given
        UnaryFunction<String, BigDecimal> first = fromStringToBigDecimal();
        UnaryFunction<BigDecimal, Long> second = fromBigDecimalToLong();
        UnaryFunction<Long, Integer> third = fromLongToInteger();
        UnaryFunction<Integer, Double> fourth = fromIntegerToDouble();
        UnaryFunction<Double, Float> fifth = fromDoubleToFloat();
        UnaryFunction<Float, BigInteger> sixth = fromFloatToBigInteger();
        UnaryFunction<BigInteger, BigInteger> seventh = toDoubledBigInteger();
        UnaryFunction<BigInteger, String> eighth = toStringValueFor(BigInteger.class);
        UnaryFunction<String, Integer> ninth = toLength();

        UnaryFunction<String, Integer> chained = UnaryFunctions.compose(
                first, second, third, fourth, fifth, sixth, seventh, eighth, ninth);

        // When
        Integer result = chained.call("1234");

        // Then
        assertThat(result, is(4));
    }

    private static Mapper<String, Integer> toLength() {
        return new Mapper<String, Integer>() {
            @Override public Integer map(String input) {
                return input.length();
            }
        };
    }

    private static Mapper<BigInteger, BigInteger> toDoubledBigInteger() {
        return new Mapper<BigInteger, BigInteger>() {
            @Override public BigInteger map(BigInteger input) {
                return input.multiply(new BigInteger("2"));
            }
        };
    }
}