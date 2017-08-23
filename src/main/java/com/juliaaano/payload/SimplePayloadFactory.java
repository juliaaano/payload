package com.juliaaano.payload;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.ProviderStrategy;

class SimplePayloadFactory implements PayloadFactory {

    private final ProviderStrategy strategy;

    SimplePayloadFactory(final ProviderStrategy strategy) {

        this.strategy = strategy;
    }

    @Override
    public <T> Payload<T> newInstance(final String content, final Class<T> type) {
        return new SimplePayload<T>(content, type) {
            @Override
            protected Provider provider() {
                return strategy.choose();
            }
        };
    }

    @Override
    public <T> Payload<T> newInstance(final T object) {
        return new SimplePayload<T>(object) {
            @Override
            protected Provider provider() {
                return strategy.choose();
            }
        };
    }
}
