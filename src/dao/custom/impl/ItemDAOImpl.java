package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import db.DBConnection;
import entity.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {


    @Override
    public String getLastItemID() {
        try {
            ResultSet resultSet = CrudUtil.execute("select * from Item order by code desc limit 1");
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

    @Override
    public List<Item> findAll() {
        try {
            ResultSet resultSet = CrudUtil.execute("select * from Item");
            List<Item> itemList = new ArrayList<>();
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

    @Override
    public Item find(String pk) {
        try {
            ResultSet resultSet = CrudUtil.execute("select * from Item where code = ?",pk);

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

    @Override
    public boolean add(Item entity) {
        return CrudUtil.execute("insert into Item values (?,?,?,?)" , entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());
    }

    @Override
    public boolean update(Item entity) {


        return CrudUtil.execute("update Item set description = ?,unitPrice = ?,qtyOnHand = ? where code = ?",entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getCode());
    }

    @Override
    public boolean delete(String pk) {
        return CrudUtil.execute("delete from Item where code = ?",pk);
    }
}
