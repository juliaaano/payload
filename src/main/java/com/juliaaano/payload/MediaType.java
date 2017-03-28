package com.juliaaano.payload;

import com.juliaaano.payload.cache.CachedProviderStrategy;
import com.juliaaano.payload.json.JsonProviderStrategy;
import com.juliaaano.payload.xml.XmlProviderStrategy;

import static java.lang.String.format;

public enum MediaType {

    JSON("application/json",
            new GenericPayloadFactory(
                    new CachedProviderStrategy(
                            new JsonProviderStrategy()
                    )
            )
    ),

    XML("application/xml",
            new GenericPayloadFactory(
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

    public static MediaType of(String mediaType) {

        mediaType = (mediaType != null) ? mediaType : "";

        if (mediaType.toLowerCase().contains("application/json"))
            return JSON;
        else if (mediaType.toLowerCase().contains("application/xml"))
            return XML;
        else
            throw new InvalidMediaTypeException(format("Invalid media type %s.", mediaType));
    }

    public PayloadFactory payload() {

        return payloadFactory;
    }

    @Override
    public String toString() {

        return mediaType;
    }
}
