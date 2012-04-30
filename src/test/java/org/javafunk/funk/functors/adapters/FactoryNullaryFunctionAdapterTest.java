package org.javafunk.funk.functors.adapters;

import org.javafunk.funk.functors.Factory;
import org.javafunk.funk.functors.functions.NullaryFunction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class FactoryNullaryFunctionAdapterTest {
    @Mock Factory<Integer> factory;

    @Test
    public void shouldDelegateCallToSuppliedAction() throws Exception {
        // Given
        Integer expected = 3;
        NullaryFunction<Integer> adapter = FactoryNullaryFunctionAdapter.factoryNullaryFunction(factory);
        given(factory.create()).willReturn(expected);

        // When
        Integer actual = adapter.call();

        // Then
        assertThat(actual, is(expected));
    }
}
