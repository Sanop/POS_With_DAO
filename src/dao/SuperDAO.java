package dao;

import entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface SuperDAO <T extends SuperEntity,ID extends Serializable>{
    List<T> findAll();

    T find(ID pk);

    boolean add(T entity);

    boolean update(T entity);

    boolean delete(ID pk);
}
