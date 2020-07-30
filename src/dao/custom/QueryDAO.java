package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    CustomEntity getOrderDetail(String id);

    CustomEntity getOrderDetail2(String id);

    List<CustomEntity> getAllOrderDetail();

    List<CustomEntity> SearchAllOrderDetail(String key);
}
