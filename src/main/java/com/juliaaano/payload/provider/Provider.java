package com.juliaaano.payload.provider;

/**
 * A Provider is an abstraction of the underlying mechanism that serializes and
 * deserializes data.
 * <p>
 * It should be extended by clients of the library who wish to support
 * additional (custom) providers.
 *
 * @author JM
 */
public interface Provider {

    /**
     * Serializes the specified object into its equivalent <i>media type</i>
     * representation.
     *
     * @param object the object for which a representation is to be created
     * @return representation of object
     */
    String serialize(Object object);

    /**
     * deserializes the specified <i>media type</i> representation into an object
     * of the specified class.
     *
     * @param <T>  the type of the desired object
     * @param raw  the string from which the object is to be deserialized
     * @param type the class of T
     * @return an object of type T from the string
     */
    <T> T deserialize(String raw, Class<T> type);
}
