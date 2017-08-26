package com.juliaaano.payload.provider.sourcing;

import com.juliaaano.payload.provider.ProviderFactory;

public interface ProviderFactorySource<T extends ProviderFactory> {

    Iterable<T> load();
}
