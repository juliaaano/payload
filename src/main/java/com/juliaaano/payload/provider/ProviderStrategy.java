package com.juliaaano.payload.provider;

/**
 * A {@link ProviderStrategy} chooses which Provider should be used considering
 * there could be many possible choices, which are usually defined and listed
 * in META-INF/services files, as per specified in Java's
 * {@link java.util.ServiceLoader}
 *
 * @author Juliano Boesel Mohr
 */
public interface ProviderStrategy {

    /**
     * Determines which provider to use.
     *
     * @return An instance of {@link Provider}
     */
    Provider choose();
}
