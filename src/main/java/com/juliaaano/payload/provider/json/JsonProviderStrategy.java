package com.juliaaano.payload.provider.json;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.ProviderException;
import com.juliaaano.payload.provider.ProviderStrategy;
import com.juliaaano.payload.provider.runtime.RuntimeProvider;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class JsonProviderStrategy implements ProviderStrategy {

    private static final List<Class<? extends RuntimeProvider>> PROVIDERS = asList(
            Gson.class,
            JsonB.class,
            Jackson.class
    );

    private final List<RuntimeProviderDef> providers;

    public JsonProviderStrategy() {

        providers = PROVIDERS.stream().map(RuntimeProviderDef::new).collect(toList());
    }

    public Provider choose() {

        for (final RuntimeProviderDef provider : providers) {

            final Optional<Provider> instance =
                    provider.newInstance()
                            .map(RuntimeProvider::setup)
                            .orElseGet(Optional::empty);

            if (instance.isPresent())
                return instance.get();
        }

        throw new ProviderException("JSON Providers not found in the classpath.");
    }

    private static final class RuntimeProviderDef {

        private final Class<? extends RuntimeProvider> clazz;

        private RuntimeProviderDef(final Class<? extends RuntimeProvider> clazz) {

            this.clazz = clazz;
        }

        private Optional<RuntimeProvider> newInstance() {

            try {

                return Optional.of(clazz.newInstance());

            } catch (InstantiationException | IllegalAccessException ex) {

                return Optional.empty();
            }
        }
    }
}
