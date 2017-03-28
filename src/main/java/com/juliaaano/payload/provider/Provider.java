package com.juliaaano.payload.provider;

public interface Provider {

    String serialize(Object object);

    <T> T deserialize(String raw, Class<T> type);
}
