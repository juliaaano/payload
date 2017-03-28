package com.juliaaano.payload.provider.json;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.ProviderException;
import com.juliaaano.payload.provider.ProviderFactory;
import com.juliaaano.payload.provider.ProviderStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.ServiceLoader;

public class JsonProviderStrategy implements ProviderStrategy {

    private static final Logger log = LoggerFactory.getLogger(JsonProviderStrategy.class);

    private final ServiceLoader<JsonProviderFactory> providers;

    public JsonProviderStrategy() {

        providers = ServiceLoader.load(JsonProviderFactory.class);
    }

    public Provider choose() {

        for (final ProviderFactory provider : providers) {

            final Optional<Provider> instance = provider.newInstance();

            if (instance.isPresent()) {
                log.info("JSON provider in use is {}.", provider);
                return instance.get();
            }
        }

        throw new ProviderException("JSON Providers not found in the classpath.");
    }
}
