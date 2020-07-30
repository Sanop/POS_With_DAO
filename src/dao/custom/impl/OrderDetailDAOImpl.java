package dao.custom.impl;

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
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from `Order` order by id desc limit 1");
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
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from OrderDetail");
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
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from OrderDetail where orderId = ? and itemCode = ?");
            preparedStatement.setObject(1,pk.getOrderID());
            preparedStatement.setObject(2,pk.getItemCode());
            ResultSet resultSet = preparedStatement.executeQuery();

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
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into OrderDetail values (?,?,?,?)");
            preparedStatement.setObject(1,entity.getOrderDetailPK().getOrderID());
            preparedStatement.setObject(2,entity.getOrderDetailPK().getItemCode());
            preparedStatement.setObject(3,entity.getQty());
            preparedStatement.setObject(4,entity.getUnitPrice());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OrderDetail entity) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update OrderDetail set qty =  ?,unitPrice = ? where orderId = ? and itemCode = ?");
            preparedStatement.setObject(1,entity.getQty());
            preparedStatement.setObject(2,entity.getUnitPrice());
            preparedStatement.setObject(3,entity.getOrderDetailPK().getOrderID());
            preparedStatement.setObject(3,entity.getOrderDetailPK().getItemCode());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OrderDetailPK pk) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete OrderDetail where orderId = ? and itemCode = ?");
            preparedStatement.setObject(1,pk.getOrderID());
            preparedStatement.setObject(2,pk.getItemCode());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
