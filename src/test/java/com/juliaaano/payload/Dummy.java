package com.juliaaano.payload;

import java.util.Objects;

public class Dummy {

    // public modifier required by providers (Jackson, JsonB)
    public String value;

    // no-args constructor required by providers (Jackson, JsonB)
    public Dummy() {
        super();
    }

    public Dummy(final String value) {

        this.value = value;
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof Dummy)) return false;

        return Objects.equals(value, ((Dummy) obj).value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
