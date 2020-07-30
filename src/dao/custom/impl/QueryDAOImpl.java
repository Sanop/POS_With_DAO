package dao.custom.impl;

import dao.custom.QueryDAO;
import db.DBConnection;
import entity.CustomEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {


    @Override
    public CustomEntity getOrderDetail(String id) {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select c.name,o.id,o.date from `Order` o " +
                    "inner join Customer c on " +
                    "o.customerId = c.id where o.id = ?;");
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new CustomEntity(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    @Override
    public CustomEntity getOrderDetail2(String id) {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select c.id,c.name,o.id from `Order` o" +
                    " inner join Customer c on " +
                    "o.customerId = c.id where o.id = ?;");
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new CustomEntity(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    @Override
    public List<CustomEntity> getAllOrderDetail() {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select o.id,o.date,c.id,c.name,SUM(od.qty * od.unitPrice) as " +
                    "total from `Order` o inner join Customer c on " +
                    "o.customerId = c.id inner join OrderDetail od on " +
                    "od.orderId = o.id group by o.id, o.date, c.id, c.name;");
            List<CustomEntity> orderList = new ArrayList<>();
            while(resultSet.next()){
                orderList.add(new CustomEntity(resultSet.getString(1),
                        Date.valueOf(resultSet.getString(2)),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        Double.parseDouble(resultSet.getString(5))));
            }
            return orderList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomEntity> SearchAllOrderDetail(String key) {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select o.id,o.date,c.id,c.name,SUM(od.qty * od.unitPrice) as total from " +
                    "`Order` o inner join Customer c on " +
                    "o.customerId = c.id inner join OrderDetail od on " +
                    "od.orderId = o.id where o.id like ? or " +
                    "o.date like ? or c.id like ? or " +
                    "c.name like ? group by " +
                    "o.id, o.date, c.id, c.name;");
            preparedStatement.setObject(1,key);
            preparedStatement.setObject(2,key);
            preparedStatement.setObject(3,key);
            preparedStatement.setObject(4,key);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<CustomEntity> entityList = new ArrayList<>();
            while (resultSet.next()) {
                entityList.add(new CustomEntity(resultSet.getString(1),
                        resultSet.getDate(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5)));
            }
            return entityList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}
