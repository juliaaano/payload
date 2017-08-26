package com.juliaaano.payload.provider.sourcing;

import com.juliaaano.payload.provider.ProviderFactory;

import java.util.ServiceLoader;

/**
 * Source provider factories using {@link java.util.ServiceLoader} with
 * implementations of {@link ProviderFactory}.
 *
 * @author Juliano Boesel Mohr
 */
public class SvcLoaderProviderFactorySource<T extends ProviderFactory> implements ProviderFactorySource<T> {

    private final Class<T> typeOfProviderFactory;

    public SvcLoaderProviderFactorySource(final Class<T> typeOfProviderFactory) {

        this.typeOfProviderFactory = typeOfProviderFactory;
    }

    @Override
    public Iterable<T> load() {

        return ServiceLoader.load(typeOfProviderFactory);
    }
}