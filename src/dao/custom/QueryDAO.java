package dao.custom;

import dao.SuperDAO;
import entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    CustomEntity getOrderDetail(String id)throws Exception;

    CustomEntity getOrderDetail2(String id)throws Exception;

    List<CustomEntity> getAllOrderDetail()throws Exception;

    List<CustomEntity> SearchAllOrderDetail(String key)throws Exception;
}
