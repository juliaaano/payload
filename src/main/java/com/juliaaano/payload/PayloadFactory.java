package com.juliaaano.payload;

/**
 * A PayloadFactory creates new instances of {@link Payload}.
 *
 * An instance of Payload is linked to a corresponding {@link MediaType},
 * therefore implementations of this factory should be media type specific,
 * such as {@link com.juliaaano.payload.xml.XmlProviderFactory}.
 *
 * @author Juliano Boesel Mohr
 */
public interface PayloadFactory {

    /**
     * Creates a new instance of {@link Payload} out of a serialized, raw
     * format.
     *
     * @param content the raw, serialized, Payload content
     *
     * @param type the {@link Class} to explicitly define this Payload type
     *
     * @param <T> the object <strong>type</strong> that represents the new
     *            Payload
     *
     * @return a new instance of Payload
     */
    <T> Payload<T> newInstance(final String content, Class<T> type);

    /**
     * Creates a new instance of {@link Payload} for a given object.
     *
     * @param object the object represented by this Payload
     *
     * @param <T> the object <strong>type</strong> that represents the new
     *            Payload
     *
     * @return a new instance of Payload
     */
    <T> Payload<T> newInstance(final T object);
}
