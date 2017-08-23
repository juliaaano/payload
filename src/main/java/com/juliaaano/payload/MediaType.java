package com.juliaaano.payload;

import com.juliaaano.payload.cache.CachedProviderStrategy;
import com.juliaaano.payload.json.JsonProviderStrategy;
import com.juliaaano.payload.xml.XmlProviderStrategy;

import static java.lang.String.format;

/**
 * Entry-point for payload conversion.
 * <p>
 * A MediaType contains an enumeration of represented <i>media types</i>
 * that are capable to transform a literal {@link String} data into a Java
 * object and vice-versa. A process also known as object data binding.
 * <p>
 * <strong>Example:</strong>
 * <blockquote><pre>
 * JSON.payload().newInstance(new MyObject()); // From object to JSON
 * JSON.payload().newInstance("{}", MyObject.class); // From JSON to object
 * </pre></blockquote>
 * A MediaType, in this context, has the same semantics as the internet MIME
 * type. Use it to retrieve {@link Payload} with the corresponding
 * {@link PayloadFactory}.
 *
 * @author Juliano Boesel Mohr
 */
public enum MediaType {

    /**
     * The <i>application/json</i> media type.
     */
    JSON("application/json",
            new SimplePayloadFactory(
                    new CachedProviderStrategy(
                            new JsonProviderStrategy()
                    )
            )
    ),

    /**
     * The <i>application/xml</i> media type.
     */
    XML("application/xml",
            new SimplePayloadFactory(
                    new CachedProviderStrategy(
                            new XmlProviderStrategy()
                    )
            )
    );

    private final String mediaType;

    private final PayloadFactory payloadFactory;

    MediaType(final String mediaType, final PayloadFactory payloadFactory) {

        this.mediaType = mediaType;
        this.payloadFactory = payloadFactory;
    }

    /**
     * Retrieves the MediaType for the given media type string.
     * <p>
     * The given mediaType string is based on the internet standards (e.g.
     * application/json).
     *
     * @param mediaType the representation of the MediaType based on internet
     *                  standards (e.g. application/xml).
     * @return the MediaType enum instance
     * @throws InvalidMediaTypeException if no corresponding type is found for
     *                                   the given mediaType
     */
    public static MediaType of(String mediaType) {

        mediaType = (mediaType != null) ? mediaType : "";

        if (mediaType.toLowerCase().contains("application/json"))
            return JSON;
        else if (mediaType.toLowerCase().contains("application/xml"))
            return XML;
        else
            throw new InvalidMediaTypeException(format("Invalid media type %s.", mediaType));
    }

    /**
     * Gets the {@link PayloadFactory} for this MediaType.
     * <p>
     * {@link Payload} cannot be created without a factory, therefore use it
     * to instantiate new payloads.
     *
     * @return the {@link PayloadFactory} for this MediaType
     */
    public PayloadFactory payload() {

        return payloadFactory;
    }

    /**
     * Returns the representation of this enum constant in the form of a
     * internet based media type (e.g. application/json).
     *
     * @return the representation of this enum constant
     */
    @Override
    public String toString() {

        return mediaType;
    }
}
