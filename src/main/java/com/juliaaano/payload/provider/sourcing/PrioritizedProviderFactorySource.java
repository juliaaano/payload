package com.juliaaano.payload.provider.sourcing;

import com.juliaaano.payload.provider.ProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static java.lang.String.format;

public abstract class PrioritizedProviderFactorySource<T extends ProviderFactory> implements ProviderFactorySource<T> {

    private final PayloadProperties properties = new PayloadProperties();

    private final ProviderFactorySource<T> source;

    public PrioritizedProviderFactorySource(final ProviderFactorySource<T> source) {

        this.source = source;
    }

    protected abstract String priorityProperty();

    protected abstract Class<?> mapToFactory(String providerKey);

    @Override
    public Iterable<T> load() {

        return discoverPriority()
                .map(this::prioritized)
                .orElseGet(source::load);
    }

    private Optional<Class<?>> discoverPriority() {

        return properties.get(priorityProperty())
                .map(String::toUpperCase)
                .map(this::mapToFactory);
    }

    private Iterable<T> prioritized(final Class<?> priority) {

        final List<T> providers = new ArrayList<>();
        source.load().forEach(providers::add);

        providers.sort((first, second) ->
                first.getClass().equals(priority) ? -1 : 1
        );

        return providers;
    }

    private static final class PayloadProperties {

        private static final Logger log = LoggerFactory.getLogger(PayloadProperties.class);

        private final Properties properties = new Properties();

        private PayloadProperties load() {

            final String fileName = "payload.properties";

            final InputStream file =
                    PrioritizedProviderFactorySource.class
                            .getClassLoader()
                            .getResourceAsStream(fileName);

            if (file != null) {
                try {
                    properties.load(file);
                } catch (IOException ex) {
                    log.warn(format("Invalid %s file.", fileName), ex);
                }
            } else {
                log.info("File {} not found, will use defaults.", fileName);
            }

            return this;
        }

        private Optional<String> get(String property) {

            final String jsonProvider = properties.getProperty(property);

            if (jsonProvider != null)
                return Optional.of(jsonProvider);
            else
                return Optional.ofNullable(load().properties.getProperty(property));
        }
    }
}
