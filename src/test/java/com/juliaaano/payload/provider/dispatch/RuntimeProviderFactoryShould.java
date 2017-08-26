package com.juliaaano.payload.provider.dispatch;

import org.junit.Test;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;

public class RuntimeProviderFactoryShould {

    @Test
    public void return_empty_optional_when_provider_not_found() {

        RuntimeProviderFactory runtimeProvider =
                new RuntimeProviderFactory(Optional::empty, null, null);

        assertThat(runtimeProvider.newInstance()).isEqualTo(empty());
    }
}
