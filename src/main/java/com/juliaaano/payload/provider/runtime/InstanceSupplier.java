package com.juliaaano.payload.provider.runtime;

import java.util.Optional;

@FunctionalInterface
public interface InstanceSupplier {

    Optional<Object> newInstance();
}
