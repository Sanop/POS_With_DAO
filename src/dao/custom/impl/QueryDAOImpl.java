package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import entity.CustomEntity;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {


    @Override
    public CustomEntity getOrderDetail(String id) throws Exception{

            ResultSet resultSet = CrudUtil.execute("select c.name,o.id,o.date from `Order` o \" +\n" +
                    "                    \"inner join Customer c on \" +\n" +
                    "                    \"o.customerId = c.id where o.id = ?",id);

            if(resultSet.next()){
                return new CustomEntity(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
            return null;

    }

    @Override
    public CustomEntity getOrderDetail2(String id)throws Exception {


            ResultSet resultSet = CrudUtil.execute("select c.id,c.name,o.id from `Order` o\" +\n" +
                    "                    \" inner join Customer c on \" +\n" +
                    "                    \"o.customerId = c.id where o.id = ?",id);
            if(resultSet.next()){
                return new CustomEntity(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
            return null;

    }

    @Override
    public List<CustomEntity> getAllOrderDetail() throws Exception{

            ResultSet resultSet = CrudUtil.execute("select o.id,o.date,c.id,c.name,SUM(od.qty * od.unitPrice) as \" +\n" +
                    "                    \"total from `Order` o inner join Customer c on \" +\n" +
                    "                    \"o.customerId = c.id inner join OrderDetail od on \" +\n" +
                    "                    \"od.orderId = o.id group by o.id, o.date, c.id, c.name");
            List<CustomEntity> orderList = new ArrayList<>();
            while(resultSet.next()){
                orderList.add(new CustomEntity(resultSet.getString(1),
                        Date.valueOf(resultSet.getString(2)),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        Double.parseDouble(resultSet.getString(5))));
            }
            return orderList;
    }

    @Override
    public List<CustomEntity> SearchAllOrderDetail(String key) throws Exception{
            ResultSet resultSet = CrudUtil.execute("select o.id,o.date,c.id,c.name,SUM(od.qty * od.unitPrice) as total from \" +\n" +
                    "                    \"`Order` o inner join Customer c on \" +\n" +
                    "                    \"o.customerId = c.id inner join OrderDetail od on \" +\n" +
                    "                    \"od.orderId = o.id where o.id like ? or \" +\n" +
                    "                    \"o.date like ? or c.id like ? or \" +\n" +
                    "                    \"c.name like ? group by \" +\n" +
                    "                    \"o.id, o.date, c.id, c.name",key,key,key,key);
            List<CustomEntity> entityList = new ArrayList<>();
            while (resultSet.next()) {
                entityList.add(new CustomEntity(resultSet.getString(1),
                        resultSet.getDate(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5)));
            }
            return entityList;
    }


}
