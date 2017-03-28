package com.juliaaano.payload.json;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.runtime.MethodDefinition;
import com.juliaaano.payload.provider.runtime.RuntimeProviderFactory;

import java.util.Optional;

public class Gson implements JsonProviderFactory {

    private final RuntimeProviderFactory provider;

    public Gson() {

        this.provider = new RuntimeProviderFactory(
                this::gsonInstance,
                new MethodDefinition("toJson", Object.class),
                new MethodDefinition("fromJson", String.class, Class.class)
        );
    }

    @Override
    public Optional<Provider> newInstance() {

        return provider.newInstance();
    }

    private Optional<Object> gsonInstance() {

        try {

            return Optional.of(
                    Class.forName("com.google.gson.Gson").newInstance()
            );

        } catch (ReflectiveOperationException ex) {

            return Optional.empty();
        }
    }
}
