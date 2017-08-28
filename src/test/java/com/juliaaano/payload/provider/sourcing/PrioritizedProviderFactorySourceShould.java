package com.juliaaano.payload.provider.sourcing;

import com.juliaaano.payload.json.*;
import com.juliaaano.payload.xml.JacksonXml;
import com.juliaaano.payload.xml.Jaxb;
import com.juliaaano.payload.xml.XmlPrioritizedProviderFactorySource;
import com.juliaaano.payload.xml.XmlProviderFactory;
import org.junit.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class PrioritizedProviderFactorySourceShould {

    @Test
    public void load_json_provider_factories_in_order() {

        final ProviderFactorySource<?> factorySource =
                new JsonPrioritizedProviderFactorySource<>(
                        new SvcLoaderProviderFactorySource<>(
                                JsonProviderFactory.class
                        )
                );

        final Iterator<?> factories = factorySource.load().iterator();

        assertThat(factories.next()).isOfAnyClassIn(JacksonJson.class);
        assertThat(factories.next()).isOfAnyClassIn(Gson.class);
        assertThat(factories.next()).isOfAnyClassIn(JsonB.class);
    }

    @Test
    public void load_xml_provider_factories_in_order() {

        final ProviderFactorySource<?> factorySource =
                new XmlPrioritizedProviderFactorySource<>(
                        new SvcLoaderProviderFactorySource<>(
                                XmlProviderFactory.class
                        )
                );

        final Iterator<?> factories = factorySource.load().iterator();

        assertThat(factories.next()).isOfAnyClassIn(JacksonXml.class);
        assertThat(factories.next()).isOfAnyClassIn(Jaxb.class);
    }
}
