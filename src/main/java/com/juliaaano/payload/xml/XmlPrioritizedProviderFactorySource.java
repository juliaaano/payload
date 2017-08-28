package com.juliaaano.payload.xml;

import com.juliaaano.payload.provider.sourcing.PrioritizedProviderFactorySource;
import com.juliaaano.payload.provider.sourcing.ProviderFactorySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlPrioritizedProviderFactorySource<T extends XmlProviderFactory> extends PrioritizedProviderFactorySource<T> {

    private static final Logger log = LoggerFactory.getLogger(XmlPrioritizedProviderFactorySource.class);

    public XmlPrioritizedProviderFactorySource(final ProviderFactorySource<T> source) {

        super(source);
    }

    @Override
    protected String priorityProperty() {

        return "xml.provider";
    }

    @Override
    protected Class<?> mapToFactory(String providerKey) {

        switch (providerKey) {
            case "JAXB":
                return Jaxb.class;
            case "JACKSONXML":
                return JacksonXml.class;
            default:
                log.info("Property {} not mapped to an instance of {}. Apply default priority.",
                        providerKey,
                        XmlProviderFactory.class.getName()
                );
                return null;
        }
    }
}
