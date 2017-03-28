package com.juliaaano.payload.provider.xml;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.ProviderException;
import com.juliaaano.payload.provider.ProviderFactory;
import com.juliaaano.payload.provider.ProviderStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.ServiceLoader;

public class XmlProviderStrategy implements ProviderStrategy {

    private static final Logger log = LoggerFactory.getLogger(XmlProviderStrategy.class);

    private final ServiceLoader<XmlProviderFactory> providers;

    public XmlProviderStrategy() {

        providers = ServiceLoader.load(XmlProviderFactory.class);
    }

    public Provider choose() {

        for (final ProviderFactory provider : providers) {

            final Optional<Provider> instance = provider.newInstance();

            if (instance.isPresent()) {
                log.info("XML provider in use is {}.", provider);
                return instance.get();
            }
        }

        throw new ProviderException("XML Providers not found in the classpath.");
    }
}
