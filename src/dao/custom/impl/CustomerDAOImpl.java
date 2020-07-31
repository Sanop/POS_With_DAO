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
    public String getLastCustomerID() throws Exception{

            ResultSet rst = CrudUtil.execute("SELECT * FROM Customer ORDER BY id DESC  LIMIT 1");
            if (rst.next()){
                return rst.getString(1);
            }else{
                return null;
            }
    }

    @Override
    public List<Customer> findAll() throws Exception{

            ResultSet resultSet = CrudUtil.execute("select * from Customer");
            List<Customer> customerList = new ArrayList<>();
            while (resultSet.next()){
                customerList.add(new Customer(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
            return customerList;
    }

    @Override
    public Customer find(String pk) throws Exception{

            ResultSet resultSet = CrudUtil.execute("select * from Customer where id = ?",pk);

            if(resultSet.next()){
                return new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
            }
            return null;
    }

    @Override
    public boolean add(Customer entity) throws Exception{
        return CrudUtil.execute("insert into Customer values (?,?,?)", entity.getId(), entity.getName(), entity.getAddress());

    }

    @Override
    public boolean update(Customer entity) throws Exception{
        return CrudUtil.execute("update Customer set name ?,address = ? where id = ?",entity.getName(),entity.getAddress(),entity.getId());
    }

    @Override
    public boolean delete(String pk)throws Exception {
        return CrudUtil.execute("delete from Customer where id = ?" , pk);
    }
}
