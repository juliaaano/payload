package com.juliaaano.payload.provider;

import java.util.Optional;

public interface ProviderFactory {

    Optional<Provider> newInstance();
}
