package com.juliaaano.payload.xml;

import com.juliaaano.payload.provider.sourcing.PrioritizedProviderFactorySource;
import com.juliaaano.payload.provider.sourcing.ProviderFactorySource;

import java.util.HashMap;
import java.util.Map;

public class XmlPrioritizedProviderFactorySource<T extends XmlProviderFactory> extends PrioritizedProviderFactorySource<T> {

    public XmlPrioritizedProviderFactorySource(final ProviderFactorySource<T> source) {

        super(source);
    }

    @Override
    protected String priorityProperty() {

        return "xml.provider";
    }

    @Override
    protected Map<String, Class<T>> providerFactories() {

        final Map providerFactories = new HashMap<>();

        providerFactories.put("JAXB", Jaxb.class);
        providerFactories.put("JACKSONXML", JacksonXml.class);

        return providerFactories;
    }
}
