package model.db;

import java.util.List;

public interface LibraryDao<T> {
    void save(T entity);

    void delete(int id);

    void update(T entity);

    T getById(int id);

    List<T> getAll();

    T getByName(String name);
}
