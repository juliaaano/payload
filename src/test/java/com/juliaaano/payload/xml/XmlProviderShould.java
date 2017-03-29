package com.juliaaano.payload.xml;

import com.juliaaano.payload.Dummy;
import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.ProviderFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static com.juliaaano.payload.DummyData.RANDOM_OBJECT;
import static com.juliaaano.payload.DummyData.RANDOM_XML;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class XmlProviderShould {

    @Parameterized.Parameters
    public static Collection<ProviderFactory[]> data() {
        return asList(new ProviderFactory[][]{
                {new JacksonXml()}, {new Jaxb()}
        });
    }

    private final ProviderFactory factory;

    public XmlProviderShould(final ProviderFactory factory) {

        this.factory = factory;
    }

    private Provider provider;

    @Before
    public void setup() {

        provider = factory.newInstance().get();
    }

    @Test
    public void return_object_from_json() {

        Dummy dummy = provider.deserialize(RANDOM_XML, Dummy.class);
        assertThat(dummy).isEqualTo(RANDOM_OBJECT);
    }

    @Test
    public void return_json_from_object() {

        final String json = provider.serialize(RANDOM_OBJECT);
        assertThat(json).containsIgnoringCase(RANDOM_XML);
    }
}
