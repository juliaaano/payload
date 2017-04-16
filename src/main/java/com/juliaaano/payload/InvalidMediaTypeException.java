package com.juliaaano.payload;

/**
 * General exception class to report internal errors with the library.
 *
 * @author Juliano Boesel Mohr
 */
public class InvalidMediaTypeException extends IllegalArgumentException {

    private static final long serialVersionUID = 183253650406525751L;

    InvalidMediaTypeException(final String message) {
        super(message);
    }

}
