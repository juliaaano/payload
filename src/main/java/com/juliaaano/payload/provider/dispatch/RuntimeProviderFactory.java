package com.juliaaano.payload.provider.dispatch;

import com.juliaaano.payload.provider.Provider;

import java.util.Optional;

public class RuntimeProviderFactory {

    private final InstanceSupplier supplier;

    private final MethodDefinition serialize;

    private final MethodDefinition deserialize;

    public RuntimeProviderFactory(final InstanceSupplier supplier,
                                  final MethodDefinition serialize,
                                  final MethodDefinition deserialize) {

        this.supplier = supplier;
        this.serialize = serialize;
        this.deserialize = deserialize;
    }

    public Optional<Provider> newInstance() {

        final Object instance = supplier.newInstance().orElse(null);

        if (instance == null)
            return Optional.empty();

        return Optional.of(
                new ReflectiveProvider(
                        instance,
                        serialize.invoker(instance.getClass()),
                        deserialize.invoker(instance.getClass())
                )
        );
    }
}
