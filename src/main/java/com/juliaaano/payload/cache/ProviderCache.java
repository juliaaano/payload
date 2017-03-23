package com.juliaaano.payload.cache;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.ProviderStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

enum ProviderCache {

    CACHE;

    private final Map<String, Provider> cache = new ConcurrentHashMap<>();

    Provider computeIfAbsent(
            final Class<? extends ProviderStrategy> key,
            final Function<? super String, ? extends Provider> mappingFunction) {

        return cache.computeIfAbsent(key.getName(), mappingFunction);
    }
}
