package com.juliaaano.payload.provider.runtime;

import com.juliaaano.payload.provider.ProviderException;

import static java.lang.String.format;

public class MethodDefinition {

    private final String name;

    private final Class<?>[] paramsTypes;

    public MethodDefinition(final String name, final Class<?>... paramsTypes) {

        this.name = name;
        this.paramsTypes = paramsTypes;
    }

    MethodInvoker invoker(final Class<?> clazz) {

        try {

            return new MethodInvoker(
                    clazz.getMethod(name, paramsTypes)
            );

        } catch (NoSuchMethodException ex) {

            throw new ProviderException(
                    format("Failed to find method %s in class %s.", name, clazz.getSimpleName()), ex);
        }
    }
}
