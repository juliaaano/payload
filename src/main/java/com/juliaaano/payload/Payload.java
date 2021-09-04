package com.juliaaano.payload;

/**
 * A Payload is an <strong>Object Oriented</strong> representation of data in a
 * <strong>raw</strong>, serialized format, such as JSON, XML, etc. The
 * supported formats are determined by {@link MediaType}.
 * <p>
 * Payload implementations are provisioned by {@link PayloadFactory}.
 *
 * @param <T> the Java object type for which this payload is represented by.
 *
 * @author JM
 */
public interface Payload<T> {

    /**
     * Provides the payload in its raw / native format as a {@link String}.
     *
     * @return the raw 'serialized' payload
     */
    String raw();

    /**
     * Provides the payload in the Java object format represented by T.
     *
     * @return the object 'deserialized' payload
     */
    T get();
}
