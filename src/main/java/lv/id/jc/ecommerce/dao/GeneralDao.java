package lv.id.jc.ecommerce.dao;

import java.util.List;
import java.util.Optional;

public interface GeneralDao<T> {
    Optional<T> getById(int id);

    List<T> getAll();

    boolean update(T entity);

}
