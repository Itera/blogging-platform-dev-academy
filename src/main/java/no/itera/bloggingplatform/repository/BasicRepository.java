package no.itera.bloggingplatform.repository;

import no.itera.bloggingplatform.model.Persistable;

import java.util.List;

public interface BasicRepository<T extends Persistable> {

    /**
     * Persist object to storage and return value that has associated key which can be used for further look-ups.
     *
     * @param toPersist object without associated key
     * @return object with associated key
     */
    T create(T toPersist);

    /**
     * Read one single object from storage
     *
     * @param id key representing object in storage
     * @return single object
     */
    T read(Long id);

    /**
     * Read all objects from storage
     *
     * @return all objects in storage for this type
     */
    List<T> readAll();

    /**
     * Updates object in storage with new value based on key
     *
     * @param toUpdate replacement to be used
     * @return updated object with correct key assigned
     */
    T update(Long id, T toUpdate);

    /**
     * Delete single object from storage
     *
     * @param id key of object to be deleted
     * @return deleted object
     */
    T delete(Long id);

    /**
     * Checks whether instance of object with this key exists in store
     * @param id key of object to check for existence
     * @return true if exists, false if not
     */
    boolean exists(Long id);

}
