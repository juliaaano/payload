package com.juliaaano.payload.provider.xml;

import com.juliaaano.payload.provider.runtime.MethodDefinition;
import com.juliaaano.payload.provider.runtime.RuntimeProvider;

import java.util.Optional;

public class JacksonXml extends RuntimeProvider {

    @Override
    protected Optional<Object> providerInstance() {

        try {

            return Optional.of(
                    Class.forName("com.fasterxml.jackson.dataformat.xml.XmlMapper").newInstance()
            );

        } catch (ReflectiveOperationException ex) {

            return Optional.empty();
        }
    }

    @Override
    protected MethodDefinition serialize() {

        return new MethodDefinition("writeValueAsString", Object.class);
    }

    @Override
    protected MethodDefinition deserialize() {

        return new MethodDefinition("readValue", String.class, Class.class);
    }
}
