package com.juliaaano.payload.cache;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.ProviderStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

enum ProviderCache {

    CACHE;

    private final Map<ProviderStrategy, Provider> cache = new ConcurrentHashMap<>();

    Provider computeIfAbsent(
            final ProviderStrategy key,
            final Function<? super ProviderStrategy, ? extends Provider> mappingFunction) {

        return cache.computeIfAbsent(key, mappingFunction);
    }
}
