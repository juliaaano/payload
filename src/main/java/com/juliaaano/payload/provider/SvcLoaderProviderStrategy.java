package com.juliaaano.payload.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.ServiceLoader;

import static java.lang.String.format;

/**
 * A {@link ProviderStrategy} that chooses based on options defined and listed
 * in META-INF/services files, as per specified in Java's
 * {@link java.util.ServiceLoader}
 *
 * @author Juliano Boesel Mohr
 */
public class SvcLoaderProviderStrategy<T extends ProviderFactory> implements ProviderStrategy {

    private static final Logger log = LoggerFactory.getLogger(SvcLoaderProviderStrategy.class);

    private final Class<T> typeOfProviderFactory;

    public SvcLoaderProviderStrategy(final Class<T> typeOfProviderFactory) {

        this.typeOfProviderFactory = typeOfProviderFactory;
    }

    public Provider choose() {

        final ServiceLoader<T> providers = ServiceLoader.load(typeOfProviderFactory);

        for (final ProviderFactory provider : providers) {

            final Optional<Provider> instance = provider.newInstance();

            if (instance.isPresent()) {

                log.info(
                        "Provider {} is assigned with {}.",
                        provider.getClass().getName(),
                        instance.get()
                );

                return instance.get();
            }
        }

        throw new ProviderException(
                format("The are no providers in the classpath that fulfill %s.", typeOfProviderFactory.getName())
        );
    }
}
