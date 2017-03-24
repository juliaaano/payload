package com.juliaaano.payload.runtime;

import com.juliaaano.payload.provider.runtime.MethodDefinition;
import com.juliaaano.payload.provider.runtime.RuntimeProvider;
import org.junit.Test;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;

public class RuntimeProviderShould {

    private MockRuntimeProvider runtimeProvider = new MockRuntimeProvider();

    @Test
    public void return_empty_optional_when_provider_not_found() {

        assertThat(runtimeProvider.setup()).isEqualTo(empty());
    }

    private static final class MockRuntimeProvider extends RuntimeProvider {

        @Override
        protected Optional<Object> providerInstance() {
            return empty();
        }

        @Override
        protected MethodDefinition serialize() {
            return null;
        }

        @Override
        protected MethodDefinition deserialize() {
            return null;
        }
    }
}
