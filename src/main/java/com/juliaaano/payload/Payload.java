package com.juliaaano.payload;

public interface Payload<T> {

    String raw();

    T get();
}
