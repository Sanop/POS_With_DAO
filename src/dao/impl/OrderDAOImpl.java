package dao.impl;

import dao.OrderDAO;
import db.DBConnection;
import entity.Customer;
import entity.Item;
import entity.Order;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    public List<Object> findAll(){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `Order`");
            List<Object> orderList = new ArrayList<>();
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

    public Object find(Object pk){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from `Order` where id = ?");
            preparedStatement.setObject(1,pk);
            ResultSet resultSet = preparedStatement.executeQuery();

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

    public boolean add(Object entity){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into `Order` values (?,?,?)");
            Order order = (Order) entity;
            preparedStatement.setObject(1,order.getId());
            preparedStatement.setObject(2,order.getDate());
            preparedStatement.setObject(3,order.getCustomerId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean update(Object entity){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Order order = (Order) entity;
            PreparedStatement preparedStatement = connection.prepareStatement("update `Order` set date =  ?,customerId = ? where id = ?");
            preparedStatement.setObject(1,order.getDate());
            preparedStatement.setObject(2,order.getCustomerId());
            preparedStatement.setObject(3,order.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean delete(Object pk){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from `Order` where id = ?");
            preparedStatement.setObject(1,pk);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
