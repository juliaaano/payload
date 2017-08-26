package com.juliaaano.payload.provider.sourcing;

import com.juliaaano.payload.json.*;
import org.junit.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class PrioritizedProviderFactorySourceShould {

    @Test
    public void load_json_provider_factories_in_order() {

        final PrioritizedProviderFactorySource<JsonProviderFactory> factorySource =
                new JsonPrioritizedProviderFactorySource<>(
                        new SvcLoaderProviderFactorySource<>(
                                JsonProviderFactory.class
                        )
                );

        Iterator<JsonProviderFactory> factories = factorySource.load().iterator();

        assertThat(factories.next()).isOfAnyClassIn(JacksonJson.class);
        assertThat(factories.next()).isOfAnyClassIn(Gson.class);
        assertThat(factories.next()).isOfAnyClassIn(JsonB.class);
    }
}
