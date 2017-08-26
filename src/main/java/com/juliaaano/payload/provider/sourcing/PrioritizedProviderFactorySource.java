package com.juliaaano.payload.provider.sourcing;

import com.juliaaano.payload.provider.ProviderFactory;

public class PrioritizedProviderFactorySource<T extends ProviderFactory> implements ProviderFactorySource<T> {

    private final ProviderFactorySource<T> source;

    public PrioritizedProviderFactorySource(final ProviderFactorySource<T> source) {

        this.source = source;
    }

    @Override
    public Iterable<T> load() {

        return source.load();
    }
}
