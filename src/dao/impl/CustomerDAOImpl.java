package dao.impl;

import dao.CustomerDAO;
import db.DBConnection;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public List<Object> findAll(){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Customer");
            List<Object> customerList = new ArrayList<>();
            while (resultSet.next()){
                customerList.add(new Customer(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
            return customerList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public Object find(Object pk) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Customer where id = ?");
            preparedStatement.setObject(1,pk);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
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
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Customer values (?,?,?)");
            Customer customer = (Customer) entity;
            preparedStatement.setObject(1,customer.getId());
            preparedStatement.setObject(2,customer.getName());
            preparedStatement.setObject(3,customer.getAddress());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean update(Object entity){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update Customer set name ?,address = ? where id = ?");
            Customer customer = (Customer) entity;
            preparedStatement.setObject(3,customer.getId());
            preparedStatement.setObject(1,customer.getName());
            preparedStatement.setObject(2,customer.getAddress());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean delete(Object pk){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from Customer where id = ?");
            preparedStatement.setObject(1,pk);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public String getLastCustomerID() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Customer ORDER BY id DESC  LIMIT 1");
            if (rst.next()){
                return rst.getString(1);
            }else{
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
