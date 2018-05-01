package no.itera.bloggingplatform.repository.memory;

import java.util.concurrent.atomic.AtomicLong;

public final class SimpleIdGenerator {

    private static final AtomicLong generator = new AtomicLong(1);

    private SimpleIdGenerator() {
    }

    public static long generate() {
        return generator.getAndIncrement();
    }

}
