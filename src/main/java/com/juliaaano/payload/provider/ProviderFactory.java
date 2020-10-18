package com.juliaaano.payload.provider;

import java.util.Optional;

/**
 * A ProviderFactory creates new instances of {@link Provider}.
 * <p>
 * It has been designed to be the main extension point for clients who wish
 * to support additional providers. Implementations of ProviderFactory are
 * listed in META-INF/services files, following {@link java.util.ServiceLoader}
 * specification.
 *
 * @author Juliano Mohr
 */
public interface ProviderFactory {

    /**
     * Creates a new instance of {@link Provider}.
     *
     * @return the new Provider instance
     */
    Optional<Provider> newInstance();
}
