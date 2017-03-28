package com.juliaaano.payload;

public interface PayloadFactory {

    <T> Payload<T> newInstance(final String content, Class<T> type);

    <T> Payload<T> newInstance(final T object);
}
