package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.CustomEntity;

public interface QueryDAO extends SuperDAO {
    CustomEntity getOrderDetail(String id);

    CustomEntity getOrderDetail2(String id);
}
