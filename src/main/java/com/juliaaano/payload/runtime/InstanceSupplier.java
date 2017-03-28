package com.juliaaano.payload.runtime;

import java.util.Optional;

@FunctionalInterface
public interface InstanceSupplier {

    Optional<Object> newInstance();
}
