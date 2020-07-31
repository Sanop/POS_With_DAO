package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailDAO;
import db.DBConnection;
import entity.OrderDetail;
import entity.OrderDetailPK;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {


    @Override
    public String getLastOrderDetailID() {

        try {
            ResultSet resultSet = CrudUtil.execute("select id from `Order` order by id desc limit 1");
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDetail> findAll() {
        try {
            ResultSet resultSet = CrudUtil.execute("select * from OrderDetail");
            List<OrderDetail> orderList = new ArrayList<>();
            while (resultSet.next()){
                orderList.add(new OrderDetail(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getBigDecimal(4)));
            }
            return orderList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderDetail find(OrderDetailPK pk) {
        try {
            ResultSet resultSet = CrudUtil.execute("select * from OrderDetail where orderId = ? and itemCode = ?",pk.getOrderID(),pk.getItemCode());

            if(resultSet.next()){
                return new OrderDetail(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getBigDecimal(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean add(OrderDetail entity) {
        return CrudUtil.execute("insert into OrderDetail values (?,?,?,?)",entity.getOrderDetailPK().getOrderID(),entity.getOrderDetailPK().getItemCode(),
                entity.getQty(),
                entity.getUnitPrice());
    }

    @Override
    public boolean update(OrderDetail entity) {
        return CrudUtil.execute("update OrderDetail set qty =  ?,unitPrice = ? where orderId = ? and itemCode = ?",entity.getQty(),entity.getUnitPrice(),
                entity.getOrderDetailPK().getOrderID(),
                entity.getOrderDetailPK().getItemCode());
    }

    @Override
    public boolean delete(OrderDetailPK pk) {

        return CrudUtil.execute("delete OrderDetail where orderId = ? and itemCode = ?",pk.getOrderID(),pk.getItemCode());
    }
}
