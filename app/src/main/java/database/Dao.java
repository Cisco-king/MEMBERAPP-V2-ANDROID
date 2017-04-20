package database;

import java.util.List;

/**
 * @author John Paul Cas
 * @since 4/20/2017
 * @version 1.0
 *
 * @param <T>
 *     The T Object
 */
public interface Dao<T> {

    long insert(T object);

    void deleteAll();

    T find(String uniqueValue);

    List<T> findAll();

}
