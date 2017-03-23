package com.juliaaano.payload;

import org.junit.Test;

import static com.juliaaano.payload.DummyData.RANDOM_OBJECT;
import static com.juliaaano.payload.MediaType.values;
import static org.assertj.core.api.Assertions.assertThat;

public class PayloadShould {

    @Test
    public void print_toString_as_raw_payload() {

        for (MediaType mediaType : values()) {

            Payload payload = mediaType.payload().newInstance(RANDOM_OBJECT);
            assertThat(payload.toString()).isEqualTo(payload.raw());
        }
    }
}
