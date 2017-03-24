package com.juliaaano.payload.provider.runtime;

import com.juliaaano.payload.provider.Provider;

import java.util.Optional;

public abstract class RuntimeProvider {

    public Optional<Provider> setup() {

        final Object instance = providerInstance().orElse(null);

        if (instance == null)
            return Optional.empty();

        return Optional.of(
                new ReflectiveProvider(
                        instance,
                        serialize().invoker(instance.getClass()),
                        deserialize().invoker(instance.getClass())
                )
        );
    }

    protected abstract Optional<Object> providerInstance();

    protected abstract MethodDefinition serialize();

    protected abstract MethodDefinition deserialize();

}
