package com.juliaaano.payload;

import org.junit.Test;

import static com.juliaaano.payload.DummyData.RANDOM_JSON;
import static com.juliaaano.payload.DummyData.RANDOM_OBJECT;
import static com.juliaaano.payload.MediaType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class MediaTypeShould {

    @Test
    public void return_json_when_applicationJson_contentType() {

        assertThat(MediaType.of("application/json; charset=utf-8")).isEqualTo(JSON);
    }

    @Test(expected = InvalidMediaTypeException.class)
    public void throw_exception_when_invalid_contentType() {

        MediaType.of("invalid");
    }

    @Test
    public void cache_provider_after_second_payload_call() {

        GenericPayload payload_1 = (GenericPayload) JSON.payload().newInstance(RANDOM_OBJECT);
        GenericPayload payload_2 = (GenericPayload) JSON.payload().newInstance(RANDOM_OBJECT);
        GenericPayload payload_3 = (GenericPayload) JSON.payload().newInstance(RANDOM_JSON, Dummy.class);

        assertThat(payload_2.provider()).isSameAs(payload_1.provider());
        assertThat(payload_3.provider()).isSameAs(payload_1.provider());
    }

    @Test
    public void print_mediaType_when_call_toString() {

        assertThat(JSON.toString()).isEqualTo("application/json");
    }

    @Test
    public void return_json_from_object() {

        Payload<Dummy> payload = JSON.payload().newInstance(RANDOM_OBJECT);
        assertThat(payload.raw()).isEqualTo(RANDOM_JSON);
    }

    @Test
    public void return_object_from_json() {

        Payload<Dummy> payload = JSON.payload().newInstance(RANDOM_JSON, Dummy.class);
        assertThat(payload.get()).isEqualTo(RANDOM_OBJECT);
    }
}
