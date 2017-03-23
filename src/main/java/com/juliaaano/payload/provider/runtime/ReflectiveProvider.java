package com.juliaaano.payload.provider.runtime;

import com.juliaaano.payload.provider.Provider;

class ReflectiveProvider implements Provider {

    private final Object instance;

    private final MethodInvoker serialize;

    private final MethodInvoker deserialize;

    ReflectiveProvider(final Object instance,
                       final MethodInvoker serialize,
                       final MethodInvoker deserialize) {

        this.instance = instance;
        this.serialize = serialize;
        this.deserialize = deserialize;
    }

    @Override
    public String serialize(final Object object) {

        return (String) serialize.invoke(instance, object);
    }

    @Override
    public <T> T deserialize(final String raw, final Class<T> type) {

        return type.cast(deserialize.invoke(instance, raw, type));
    }
}
