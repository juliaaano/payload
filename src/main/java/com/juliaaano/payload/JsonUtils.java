package com.juliaaano.payload;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static com.juliaaano.payload.MediaType.JSON;

/**
 * Static JSON wrapper for easier access
 */
public class JsonUtils {

    public static String toJson(final Object obj) {
        return JSON.payload().newInstance(obj).raw();
    }

    public static <T> T fromJson(final String json, final Class<T> clazz) {

        return JSON.payload().newInstance(json, clazz).get();
    }

    public static <T> T fromJson(final InputStream in, final Class<T> clazz) throws FileNotFoundException {
        String string = new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.joining("\n"));
        return fromJson(string, clazz);
    }
}
