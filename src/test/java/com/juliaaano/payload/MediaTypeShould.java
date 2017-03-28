package com.juliaaano.payload;

import org.junit.Test;

import static com.juliaaano.payload.DummyData.*;
import static com.juliaaano.payload.MediaType.JSON;
import static com.juliaaano.payload.MediaType.XML;
import static org.assertj.core.api.Assertions.assertThat;

public class MediaTypeShould {

    @Test
    public void return_enum_that_matches_mediaType() {

        assertThat(MediaType.of("application/json; charset=utf-8")).isEqualTo(JSON);
        assertThat(MediaType.of("application/xml; charset=utf-8")).isEqualTo(XML);
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
        assertThat(XML.toString()).isEqualTo("application/xml");
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

    @Test
    public void return_xml_from_object() {

        Payload<Dummy> payload = XML.payload().newInstance(RANDOM_OBJECT);
        assertThat(payload.raw()).isEqualTo(RANDOM_XML);
    }

    @Test
    public void return_object_from_xml() {

        Payload<Dummy> payload = XML.payload().newInstance(RANDOM_XML, Dummy.class);
        assertThat(payload.get()).isEqualTo(RANDOM_OBJECT);
    }
}
