package com.juliaaano.payload;

import com.juliaaano.payload.provider.Provider;

abstract class GenericPayload<T> implements Payload<T> {

    private final String raw;

    private final T object;

    GenericPayload(final String raw, final Class<T> type) {

        this.raw = raw;
        this.object = provider().deserialize(raw, type);
    }

    GenericPayload(final T object) {

        this.raw = provider().serialize(object);
        this.object = object;
    }

    protected abstract Provider provider();

    @Override
    public String raw() {

        return raw;
    }

    @Override
    public T get() {

        return object;
    }

    @Override
    public String toString() {

        return raw;
    }

    //TODO equals, hashcode
}
