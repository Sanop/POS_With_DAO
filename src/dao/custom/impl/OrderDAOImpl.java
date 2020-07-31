package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import db.DBConnection;
import entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {


    @Override
    public List<Order> findAll() {
        try {
            ResultSet resultSet = CrudUtil.execute("select * from `Order`");
            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()){
                orderList.add(new Order(resultSet.getString(1),
                        resultSet.getDate(2),
                        resultSet.getString(3)));
            }
            return orderList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Order find(String pk) {
        try {
            ResultSet resultSet = CrudUtil.execute("select * from `Order` where id = ?",pk);

            if(resultSet.next()){
                return new Order(resultSet.getString(1),
                        resultSet.getDate(2),
                        resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean add(Order entity) {

        return CrudUtil.execute("insert into `Order` values (?,?,?)",entity.getId(),entity.getDate(),entity.getCustomerId());
    }

    @Override
    public boolean update(Order entity) {
        return CrudUtil.execute("update `Order` set date =  ?,customerId = ? where id = ?",entity.getDate(),entity.getCustomerId(),entity.getId());
    }

    @Override
    public boolean delete(String pk) {
        return CrudUtil.execute("delete from `Order` where id = ?",pk);
    }
}
