package com.juliaaano.payload.xml;

import com.juliaaano.payload.provider.Provider;
import com.juliaaano.payload.provider.ProviderException;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Optional;

public class Jaxb implements XmlProviderFactory {

    @Override
    public Optional<Provider> newInstance() {

        return Optional.of(new JaxbProvider());
    }

    private static final class JaxbProvider implements Provider {

        @Override
        public String serialize(final Object object) {

            return marshal(object);
        }

        @Override
        public <T> T deserialize(final String raw, final Class<T> type) {

            return JAXB.unmarshal(new StringReader(raw), type);
        }

        @Override
        public String toString() {

            return this.getClass().getName();
        }

        private static String marshal(final Object object) {

            final StringWriter writer = new StringWriter();

            try {

                JAXBContext.newInstance(object.getClass())
                        .createMarshaller()
                        .marshal(object, writer);

            } catch (JAXBException ex) {

                throw new ProviderException("Failed to marshal XML payload.", ex);
            }

            return writer.toString();
        }
    }
}
