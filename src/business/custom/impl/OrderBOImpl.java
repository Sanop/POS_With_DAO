package business.custom.impl;

import business.custom.OrderBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;
import db.DBConnection;
import entity.Item;
import entity.Order;
import entity.OrderDetail;
import util.OrderDetailTM;
import util.OrderTM;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    public boolean placeOrder(OrderTM order, List<OrderDetailTM> orderDetails){
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            try {
                connection.setAutoCommit(false);

                OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOType.ORDER);
                boolean b = orderDAO.add(new Order(order.getOrderId(), Date.valueOf(order.getOrderDate()), order.getCustomerId()));
                if(!b){
                    connection.rollback();
                    return false;
                }

                for (OrderDetailTM orderDetail : orderDetails) {

                    OrderDetailDAO orderDetailDAO =DAOFactory.getInstance().getDAO(DAOType.ORDER_DETAIL);
                    b = orderDetailDAO.add(new OrderDetail(order.getOrderId(), orderDetail.getCode(), orderDetail.getQty(), BigDecimal.valueOf(orderDetail.getUnitPrice())));
                    if(!b){
                        connection.rollback();
                        return false;
                    }

                    ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);

                    Item item = itemDAO.find(orderDetail.getCode());
                    item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getQty());

                    b = itemDAO.update(new Item(orderDetail.getCode(), orderDetail.getDescription(), BigDecimal.valueOf(orderDetail.getUnitPrice()), item.getQtyOnHand()));

                    if(!b){
                        connection.rollback();
                        return false;
                    }

                }



                connection.commit();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();

                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return false;
            }finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public String autoGeneratePlaceOrderID(){
        try {
            OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOType.ORDER_DETAIL);
            String oldID = orderDetailDAO.getLastOrderDetailID();
            oldID = oldID.substring(2, 5);

            int newID = Integer.parseInt(oldID) + 1;

            if (newID < 10) {
                return  "OD00" + newID;
            } else if (newID < 100) {
                return  "OD0" + newID;
            } else {
                return  "OD" + newID;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
