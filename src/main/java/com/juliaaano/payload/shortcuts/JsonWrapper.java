package com.juliaaano.payload.shortcuts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.juliaaano.payload.MediaType.JSON;
import static java.util.stream.Collectors.joining;

/**
 * Static JSON wrapper for easier access.
 *
 * @author Dmitry Yakubovsky
 */
public class JsonWrapper {

    public static String toJson(final Object obj) {
        return JSON.payload().newInstance(obj).raw();
    }

    public static <T> T fromJson(final String json, final Class<T> clazz) {
        return JSON.payload().newInstance(json, clazz).get();
    }

    public static <T> T fromJson(final InputStream in, final Class<T> clazz) throws FileNotFoundException {

        final String string = new BufferedReader(new InputStreamReader(in))
                .lines()
                .collect(joining("\n"));

        return fromJson(string, clazz);
    }
}
