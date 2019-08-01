package com.juliaaano.payload.xml;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.dispatch.MethodDefinition;
import com.juliaaano.payload.provider.dispatch.RuntimeProviderFactory;

import java.util.Optional;

public class JacksonXml implements XmlProviderFactory {

    private final RuntimeProviderFactory provider;

    public JacksonXml() {

        this.provider = new RuntimeProviderFactory(
                this::xmlMapperInstance,
                new MethodDefinition("writeValueAsString", Object.class),
                new MethodDefinition("readValue", String.class, Class.class)
        );
    }

    @Override
    public Optional<Provider> newInstance() {

        return provider.newInstance();
    }

    private Optional<Object> xmlMapperInstance() {

        try {

            return Optional.of(
                    Class.forName("com.fasterxml.jackson.dataformat.xml.XmlMapper").getDeclaredConstructor().newInstance()
            );

        } catch (ReflectiveOperationException ex) {

            return Optional.empty();
        }
    }
}
