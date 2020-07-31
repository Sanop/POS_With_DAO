package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import db.DBConnection;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public String getLastCustomerID() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM Customer ORDER BY id DESC  LIMIT 1");
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

    @Override
    public List<Customer> findAll() {
        try {
            ResultSet resultSet = CrudUtil.execute("select * from Customer");
            List<Customer> customerList = new ArrayList<>();
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

    @Override
    public Customer find(String pk) {
        try {
            ResultSet resultSet = CrudUtil.execute("select * from Customer where id = ?",pk);

            if(resultSet.next()){
                return new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean add(Customer entity) {
        return CrudUtil.execute("insert into Customer values (?,?,?)", entity.getId(), entity.getName(), entity.getAddress());

    }

    @Override
    public boolean update(Customer entity) {
        return CrudUtil.execute("update Customer set name ?,address = ? where id = ?",entity.getName(),entity.getAddress(),entity.getId());
    }

    @Override
    public boolean delete(String pk) {
        return CrudUtil.execute("delete from Customer where id = ?" , pk);
    }
}
