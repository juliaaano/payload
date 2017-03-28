package com.juliaaano.payload.runtime;

import com.juliaaano.payload.provider.ProviderException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.String.format;

class MethodInvoker {

    private final Method method;

    MethodInvoker(final Method method) {

        this.method = method;
    }

    Object invoke(final Object instance, final Object... params) {

        try {

            return method.invoke(instance, params);

        } catch (IllegalAccessException | InvocationTargetException ex) {

            throw new ProviderException(format("Failed to invoke method %s.", method), ex);
        }
    }
}
