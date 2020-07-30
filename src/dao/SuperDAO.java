package dao;

import java.util.List;

public interface SuperDAO <T,ID>{
    List<T> findAll();

    T find(ID pk);

    boolean add(T entity);

    boolean update(T entity);

    boolean delete(ID pk);
}
