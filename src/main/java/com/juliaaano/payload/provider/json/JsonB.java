package com.juliaaano.payload.provider.json;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.runtime.MethodDefinition;
import com.juliaaano.payload.provider.runtime.RuntimeProviderFactory;

import java.util.Optional;

public class JsonB implements JsonProviderFactory {

    private final RuntimeProviderFactory provider;

    public JsonB() {

        this.provider = new RuntimeProviderFactory(
                this::jsonBInstance,
                new MethodDefinition("toJson", Object.class),
                new MethodDefinition("fromJson", String.class, Class.class)
        );
    }

    @Override
    public Optional<Provider> newInstance() {

        return provider.newInstance();
    }

    private Optional<Object> jsonBInstance() {

        try {

            return Optional.of(
                    Class.forName("javax.json.bind.JsonbBuilder")
                            .getDeclaredMethod("create")
                            .invoke(null)
            );

        } catch (ReflectiveOperationException ex) {

            return Optional.empty();
        }
    }
}
