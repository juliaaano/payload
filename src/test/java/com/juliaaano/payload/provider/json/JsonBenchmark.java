package com.juliaaano.payload.provider.json;

import com.google.gson.Gson;
import com.juliaaano.payload.Dummy;
import com.juliaaano.payload.Payload;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import static com.juliaaano.payload.MediaType.JSON;
import static java.lang.String.format;
import static java.util.Collections.synchronizedList;
import static java.util.UUID.randomUUID;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

@FixMethodOrder(NAME_ASCENDING)
public class JsonBenchmark {

    private static final Logger logger = LoggerFactory.getLogger(JsonBenchmark.class);

    private static final Gson gson = new Gson();

    private static final int FIFTEEN_15 = 15;

    private static final int ONE_MILLION = 1_000_000;

    private static final Random random = new Random();

    private static final List<String> randomJsonPool = new ArrayList<>();

    private static void logInfo(final Description description, final long nanos) {

        final String testName = description.getMethodName();
        logger.info("Test {} {} milliseconds", testName, NANOSECONDS.toMillis(nanos));
    }

    @Rule
    public final Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void succeeded(final long nanos, final Description description) {
            logInfo(description, nanos);
        }
    };

    @BeforeClass
    public static void beforeClass() {

        for (int i = 0; i < ONE_MILLION; i++)
            randomJsonPool.add(format("{\"value\":\"%s\"}", randomUUID().toString()));
    }

    @Test
    public void A0_warm_up() throws InterruptedException {

        List<String> result = synchronizedList(new ArrayList<>());

        ExecutorService executor = newFixedThreadPool(FIFTEEN_15);

        for (int i = 0; i < ONE_MILLION; i++) {
            executor.submit(() -> {
                String value = randomJsonPool.get(random.nextInt(ONE_MILLION));
                Payload<Dummy> payload = JSON.payload().newInstance(new Dummy(value));
                result.add(payload.raw());
                result.add(gson.toJson(new Dummy(value)));
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, SECONDS);

        assertThat(result.size()).isEqualTo(ONE_MILLION * 2);
    }

    @Test
    public void A1_serialize_when_using_reflective_gson() throws InterruptedException {

        List<String> result = synchronizedList(new ArrayList<>());

        ExecutorService executor = newFixedThreadPool(FIFTEEN_15);

        for (int i = 0; i < ONE_MILLION; i++) {
            executor.submit(() -> {
                String value = randomJsonPool.get(random.nextInt(ONE_MILLION));
                Payload<Dummy> payload = JSON.payload().newInstance(new Dummy(value));
                result.add(payload.raw());
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, SECONDS);

        assertThat(result.size()).isEqualTo(ONE_MILLION);
    }

    @Test
    public void A2_serialize_when_using_direct_gson() throws InterruptedException {

        List<String> result = synchronizedList(new ArrayList<>());

        ExecutorService executor = newFixedThreadPool(FIFTEEN_15);

        for (int i = 0; i < ONE_MILLION; i++) {
            executor.submit(() -> {
                String value = randomJsonPool.get(random.nextInt(ONE_MILLION));
                result.add(gson.toJson(new Dummy(value)));
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, SECONDS);

        assertThat(result.size()).isEqualTo(ONE_MILLION);
    }

    @Test
    public void B0_warm_up() throws InterruptedException {

        List<Dummy> result = synchronizedList(new ArrayList<>());

        ExecutorService executor = newFixedThreadPool(FIFTEEN_15);

        for (int i = 0; i < ONE_MILLION; i++) {
            executor.submit(() -> {
                String json = randomJsonPool.get(random.nextInt(ONE_MILLION));
                Payload<Dummy> payload = JSON.payload().newInstance(json, Dummy.class);
                result.add(payload.get());
                result.add(gson.fromJson(json, Dummy.class));
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, SECONDS);

        assertThat(result.size()).isEqualTo(ONE_MILLION * 2);
    }

    @Test
    public void B1_deserialize_when_using_reflective_gson() throws InterruptedException {

        List<Dummy> result = synchronizedList(new ArrayList<>());

        ExecutorService executor = newFixedThreadPool(FIFTEEN_15);

        for (int i = 0; i < ONE_MILLION; i++) {
            executor.submit(() -> {
                String json = randomJsonPool.get(random.nextInt(ONE_MILLION));
                Payload<Dummy> payload = JSON.payload().newInstance(json, Dummy.class);
                result.add(payload.get());
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, SECONDS);

        assertThat(result.size()).isEqualTo(ONE_MILLION);
    }

    @Test
    public void B2_deserialize_when_using_direct_gson() throws InterruptedException {

        List<Dummy> result = synchronizedList(new ArrayList<>());

        ExecutorService executor = newFixedThreadPool(FIFTEEN_15);

        for (int i = 0; i < ONE_MILLION; i++) {
            executor.submit(() -> {
                String json = randomJsonPool.get(random.nextInt(ONE_MILLION));
                result.add(gson.fromJson(json, Dummy.class));
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, SECONDS);

        assertThat(result.size()).isEqualTo(ONE_MILLION);
    }
}
