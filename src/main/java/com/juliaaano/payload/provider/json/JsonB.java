package com.juliaaano.payload.provider.json;

import com.juliaaano.payload.provider.runtime.MethodDefinition;
import com.juliaaano.payload.provider.runtime.RuntimeProvider;

import java.util.Optional;

class JsonB extends RuntimeProvider {

    @Override
    protected Optional<Object> providerInstance() {

        try {

            return Optional.of(
                    Class.forName("javax.json.bind.JsonbBuilder")
                            .getDeclaredMethod("create")
                            .invoke(null)
            );

        } catch (ReflectiveOperationException ex) {

            return Optional.empty();
        }
    }

    @Override
    protected MethodDefinition serialize() {

        return new MethodDefinition("toJson", Object.class);
    }

    @Override
    protected MethodDefinition deserialize() {

        return new MethodDefinition("fromJson", String.class, Class.class);
    }
}
