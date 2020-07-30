package dao;

import entity.Customer;

import java.util.List;

public interface SuperDAO {
    List<Object> findAll();

    Object find(Object pk);

    boolean add(Object entity);

    boolean update(Object entity);

    boolean delete(Object pk);
}
