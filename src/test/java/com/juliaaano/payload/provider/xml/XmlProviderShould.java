package com.juliaaano.payload.provider.xml;

import com.juliaaano.payload.Dummy;
import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.runtime.RuntimeProvider;
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
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {new JacksonXml()}
        });
    }

    private final RuntimeProvider runtimeProvider;

    public XmlProviderShould(final RuntimeProvider runtimeProvider) {

        this.runtimeProvider = runtimeProvider;
    }

    private Provider provider;

    @Before
    public void setup() {

        provider = runtimeProvider.setup().get();
    }

    @Test
    public void return_object_from_json() {

        Dummy dummy = provider.deserialize(RANDOM_XML, Dummy.class);
        assertThat(dummy).isEqualTo(RANDOM_OBJECT);
    }

    @Test
    public void return_json_from_object() {

        final String json = provider.serialize(RANDOM_OBJECT);
        assertThat(json).isEqualTo(RANDOM_XML);
    }
}
