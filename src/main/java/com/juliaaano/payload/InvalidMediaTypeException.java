package com.juliaaano.payload;

/**
 * Thrown to indicate that a client has passed an invalid or unsupported
 * {@link MediaType}.
 *
 * @author Juliano Boesel Mohr
 */
public class InvalidMediaTypeException extends IllegalArgumentException {

    private static final long serialVersionUID = 183253650406525751L;

    InvalidMediaTypeException(final String message) {
        super(message);
    }

}
