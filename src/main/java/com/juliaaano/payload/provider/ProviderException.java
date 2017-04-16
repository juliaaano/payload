package com.juliaaano.payload.provider;

/**
 * General internal exception to be thrown when there are Provider related
 * issues.
 *
 * @author Juliano Boesel Mohr
 */
public class ProviderException extends RuntimeException {

    public ProviderException(final String message) {
        super(message);
    }

    public ProviderException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
