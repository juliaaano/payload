package com.juliaaano.payload.provider.json;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.runtime.MethodDefinition;
import com.juliaaano.payload.provider.runtime.RuntimeProviderFactory;

import java.util.Optional;

public class Jackson implements JsonProviderFactory {

    private final RuntimeProviderFactory provider;

    public Jackson() {

        this.provider = new RuntimeProviderFactory(
                this::objectMapperInstance,
                new MethodDefinition("writeValueAsString", Object.class),
                new MethodDefinition("readValue", String.class, Class.class)
        );
    }

    @Override
    public Optional<Provider> newInstance() {

        return provider.newInstance();
    }

    private Optional<Object> objectMapperInstance() {

        try {

            return Optional.of(
                    Class.forName("com.fasterxml.jackson.databind.ObjectMapper").newInstance()
            );

        } catch (ReflectiveOperationException ex) {

            return Optional.empty();
        }
    }
}
