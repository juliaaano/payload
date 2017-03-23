package com.juliaaano.payload;

public class InvalidMediaTypeException extends IllegalArgumentException {

    private static final long serialVersionUID = 183253650406525751L;

    InvalidMediaTypeException(final String message) {
        super(message);
    }

}
