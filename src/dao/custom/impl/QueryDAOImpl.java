package dao.custom.impl;

import dao.custom.QueryDAO;
import db.DBConnection;
import entity.CustomEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
