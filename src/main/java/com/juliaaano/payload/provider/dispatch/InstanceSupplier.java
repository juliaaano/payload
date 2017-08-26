package com.juliaaano.payload.provider.dispatch;

import java.util.Optional;

@FunctionalInterface
public interface InstanceSupplier {

    Optional<Object> newInstance();
}
