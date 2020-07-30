package dao.impl;

import dao.ItemDAO;
import db.DBConnection;
import entity.Customer;
import entity.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    public List<Object> findAll(){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Item");
            List<Object> itemList = new ArrayList<>();
            while (resultSet.next()){
                itemList.add(new Item(resultSet.getString(1),
                        resultSet.getString(2),
                        new BigDecimal(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4))));
            }
            return itemList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public Object find(Object pk){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Item where code = ?");
            preparedStatement.setObject(1,pk);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new Item(resultSet.getString(1),
                        resultSet.getString(2),
                        new BigDecimal(resultSet.getString(3)),
                                Integer.parseInt(resultSet.getString(4)));
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
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Item values (?,?,?,?)");
            Item item = (Item) entity;
            preparedStatement.setObject(1,item.getCode());
            preparedStatement.setObject(2,item.getDescription());
            preparedStatement.setObject(3,item.getUnitPrice());
            preparedStatement.setObject(4,item.getQtyOnHand());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean update(Object entity){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Item item = (Item) entity;
            PreparedStatement preparedStatement = connection.prepareStatement("update Item set description = ?,unitPrice = ?,qtyOnHand = ? where code = ?");
            preparedStatement.setObject(1,item.getDescription());
            preparedStatement.setObject(2,item.getUnitPrice());
            preparedStatement.setObject(3,item.getQtyOnHand());
            preparedStatement.setObject(4,item.getCode());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean delete(Object pk){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from Item where code = ?");
            preparedStatement.setObject(1,pk);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public String getLastItemID() {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Item order by code desc limit 1");
            if(resultSet.next()){
                return resultSet.getString(1);
            }else{
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
