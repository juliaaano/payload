package com.juliaaano.payload.provider;

/**
 * A {@link ProviderStrategy} chooses which Provider should be used.
 * Depending on the implementation, providers can be sourced and
 * prioritized according to global configurations and/or
 * META-INF/services files, as per specified in Java's
 * {@link java.util.ServiceLoader}
 *
 * @author Juliano Mohr
 */
public interface ProviderStrategy {

    /**
     * Determines which provider to use.
     *
     * @return An instance of {@link Provider}
     */
    Provider choose();
}
