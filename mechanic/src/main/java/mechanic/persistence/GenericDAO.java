package mechanic.persistence;

import java.util.List;

public interface GenericDAO<T> {
    void save(T entity);
    List<T> listAll();
    T findById(int id);
    boolean update(T entity);
    boolean delete(int id);
}
