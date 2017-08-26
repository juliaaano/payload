package com.juliaaano.payload.provider.sourcing;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.ProviderException;
import com.juliaaano.payload.provider.ProviderFactory;
import com.juliaaano.payload.provider.ProviderStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static java.lang.String.format;

/**
 * This strategy enables custom mechanisms to supply provider factories
 * through the {@link ProviderFactorySource}.
 *
 * @author Juliano Boesel Mohr
 */
public class SourcingProviderStrategy<T extends ProviderFactory> implements ProviderStrategy {

    private static final Logger log = LoggerFactory.getLogger(SourcingProviderStrategy.class);

    private final ProviderFactorySource<T> providerFactorySource;

    public SourcingProviderStrategy(final ProviderFactorySource<T> providerFactorySource) {

        this.providerFactorySource = providerFactorySource;
    }

    @Override
    public Provider choose() {

        for (final ProviderFactory provider : providerFactorySource.load()) {

            final Optional<Provider> instance = provider.newInstance();

            if (instance.isPresent()) {

                log.info(
                        "Provider {} selected from {}.",
                        instance.get(),
                        provider.getClass().getName()
                );

                return instance.get();
            }
        }

        throw new ProviderException(
                format("The are no providers available in %s.", providerFactorySource)
        );
    }
}
