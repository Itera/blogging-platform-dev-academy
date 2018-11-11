package no.itera.bloggingplatform.repository.memory;

import no.itera.bloggingplatform.model.Persistable;
import no.itera.bloggingplatform.repository.BasicRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public abstract class DefaultInMemoryRepository<T extends Persistable> implements BasicRepository<T> {

    private final Map<Long, T> inMemoryCache = new HashMap<>();

    @Override
    public T create(T toPersist) {
        if (toPersist.getKey() != null && exists(toPersist.getKey())) {
            throw new IllegalStateException(format("Entry with key %d already exists in storage.", toPersist.getKey()));
        }
        toPersist.setKey(SimpleIdGenerator.generate());
        inMemoryCache.put(toPersist.getKey(), toPersist);
        return toPersist;
    }

    @Override
    public T read(Long id) {
        return inMemoryCache.get(id);
    }

    @Override
    public List<T> readAll() {
        return new ArrayList<>(inMemoryCache.values());
    }

    @Override
    public T update(T toUpdate) {
        if (!exists(toUpdate.getKey())) {
            throw new IllegalStateException(format("Entry with key %d does not exist in storage.", toUpdate.getKey()));
        }
        inMemoryCache.put(toUpdate.getKey(), toUpdate);
        return toUpdate;
    }

    @Override
    public T delete(Long id) {
        return inMemoryCache.remove(id);
    }

    @Override
    public boolean exists(Long id) {
        return id == null || inMemoryCache.containsKey(id);
    }
}
