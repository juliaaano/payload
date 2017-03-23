package com.juliaaano.payload;

import static java.lang.String.format;
import static java.util.UUID.randomUUID;

public class DummyData {

    private static final String randomValue = randomUUID().toString();

    public static final Dummy RANDOM_OBJECT = new Dummy(randomValue);

    public static final String RANDOM_JSON = format("{\"value\":\"%s\"}", randomValue);
}
