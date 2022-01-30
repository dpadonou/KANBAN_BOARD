package dao.generic;

import java.util.List;

public interface DaoInterface<T, K> {

    T save(T t);

    List<T> getAll();

    T findById(K id);

    void delete(K id);

    T update(T t, K id);
}
