package no.itera.bloggingplatform.repository.memory;

import no.itera.bloggingplatform.model.Persistable;
import no.itera.bloggingplatform.repository.BasicRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BasicRepositoryImpl<T extends Persistable> implements BasicRepository<T> {

    private Map<Long, T> inMemoryStorage = new HashMap<>();
    private Long id = 1L;

    @Override
    public T create(T toPersist) {
        toPersist.setKey(id++);
        inMemoryStorage.put(toPersist.getKey(), toPersist);
        return toPersist;
    }

    @Override
    public T read(Long id) {
        return inMemoryStorage.get(id);
    }

    @Override
    public List<T> readAll() {
        return new ArrayList<>(inMemoryStorage.values());
    }

    @Override
    public T update(T toUpdate) {
        inMemoryStorage.put(toUpdate.getKey(), toUpdate);
        return toUpdate;
    }

    @Override
    public T delete(Long id) {
        T deleted = inMemoryStorage.get(id);
        inMemoryStorage.remove(id);
        return deleted;
    }

    @Override
    public boolean exists(Long id) {
        return inMemoryStorage.containsKey(id);
    }
}
