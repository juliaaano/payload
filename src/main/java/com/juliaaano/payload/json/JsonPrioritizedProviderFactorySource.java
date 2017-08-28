package com.juliaaano.payload.json;

import com.juliaaano.payload.provider.sourcing.PrioritizedProviderFactorySource;
import com.juliaaano.payload.provider.sourcing.ProviderFactorySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.unmodifiableMap;

public class JsonPrioritizedProviderFactorySource<T extends JsonProviderFactory> extends PrioritizedProviderFactorySource<T> {

    private static final Logger log = LoggerFactory.getLogger(JsonPrioritizedProviderFactorySource.class);

    private static final Map<String, Class<?>> PROVIDER_FACTORIES;

    static {

        final HashMap<String, Class<? extends JsonProviderFactory>> providerFactories = new HashMap<>();

        providerFactories.put("GSON", Gson.class);
        providerFactories.put("JACKSONJSON", JacksonJson.class);
        providerFactories.put("JSONB", JsonB.class);

        PROVIDER_FACTORIES = unmodifiableMap(providerFactories);
    }

    public JsonPrioritizedProviderFactorySource(final ProviderFactorySource<T> source) {

        super(source);
    }

    @Override
    protected String priorityProperty() {

        return "json.provider";
    }

    @Override
    protected Class<?> mapToFactory(final String providerKey) {

        return Optional.ofNullable(PROVIDER_FACTORIES.get(providerKey))
                .orElseGet(() -> {
                    log.info("Property {} not mapped to an instance of {}. Apply default priority.",
                            providerKey,
                            JsonProviderFactory.class.getName()
                    );
                    return null;
                });
    }
}
