package com.juliaaano.payload;

import com.juliaaano.payload.provider.Provider;

import java.util.Objects;

abstract class SimplePayload<T> implements Payload<T> {

    private final String raw;

    private final T object;

    SimplePayload(final String raw, final Class<T> type) {

        this.raw = raw;
        this.object = provider().deserialize(raw, type);
    }

    SimplePayload(final T object) {

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

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof SimplePayload)) return false;

        return Objects.equals(object, ((SimplePayload<?>) obj).object);
    }

    @Override
    public int hashCode() {

        return Objects.hash(object);
    }
}
