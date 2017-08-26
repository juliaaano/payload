package com.juliaaano.payload.json;

import com.juliaaano.payload.provider.sourcing.PrioritizedProviderFactorySource;
import com.juliaaano.payload.provider.sourcing.ProviderFactorySource;

import java.util.HashMap;
import java.util.Map;

public class JsonPrioritizedProviderFactorySource<T extends JsonProviderFactory> extends PrioritizedProviderFactorySource<T> {

    public JsonPrioritizedProviderFactorySource(final ProviderFactorySource<T> source) {

        super(source);
    }

    @Override
    protected String priorityProperty() {

        return "json.provider";
    }

    @Override
    protected Map<String, Class<T>> providerFactories() {

        final HashMap<String, Class<T>> providerFactories = new HashMap<>();

        providerFactories.put("GSON", (Class<T>) Gson.class);
        providerFactories.put("JACKSONJSON", (Class<T>) JacksonJson.class);
        providerFactories.put("JSONB", (Class<T>) JsonB.class);

        return providerFactories;
    }
}
