package no.itera.bloggingplatform.repository.memory;

import java.util.concurrent.atomic.AtomicLong;

final class SimpleIdGenerator {

    private static final AtomicLong generator = new AtomicLong(1);

    private SimpleIdGenerator() {
    }

    static long generate() {
        return generator.getAndIncrement();
    }

}
