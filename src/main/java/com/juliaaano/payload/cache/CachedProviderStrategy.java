package com.juliaaano.payload.cache;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.ProviderStrategy;

import static com.juliaaano.payload.cache.ProviderCache.CACHE;

public class CachedProviderStrategy implements ProviderStrategy {

    private final ProviderStrategy strategy;

    public CachedProviderStrategy(final ProviderStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Provider choose() {

        return CACHE.computeIfAbsent(strategy.getClass(), s -> strategy.choose());
    }
}
