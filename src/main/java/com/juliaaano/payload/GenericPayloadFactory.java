package com.juliaaano.payload;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.ProviderStrategy;

class GenericPayloadFactory implements PayloadFactory {

    private final ProviderStrategy strategy;

    GenericPayloadFactory(final ProviderStrategy strategy) {

        this.strategy = strategy;
    }

    @Override
    public <T> Payload<T> newInstance(final String content, final Class<T> type) {
        return new GenericPayload<T>(content, type) {
            @Override
            protected Provider provider() {
                return strategy.choose();
            }
        };
    }

    @Override
    public <T> Payload<T> newInstance(final T object) {
        return new GenericPayload<T>(object) {
            @Override
            protected Provider provider() {
                return strategy.choose();
            }
        };
    }
}
