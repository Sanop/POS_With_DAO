package business;

import dao.*;
import db.DBConnection;
import entity.Customer;
import entity.Item;
import entity.Order;
import entity.OrderDetail;
import util.CustomerTM;
import util.ItemTM;
import util.OrderDetailTM;
import util.OrderTM;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessLogic {

    public static String getNewItemCode(){
        String lastItemCode = new ItemDAO().getLastCustomerID();
        if (lastItemCode == null){
            return "I001";
        }else{
            int maxId=  Integer.parseInt(lastItemCode.replace("I",""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "I00" + maxId;
            } else if (maxId < 100) {
                id = "I0" + maxId;
            } else {
                id = "I" + maxId;
            }
            return id;
        }
    }

    public static String getNewCustomerId(){

        String lastCustomerId = new CustomerDAO().getLastCustomerID();
        if (lastCustomerId == null){
            return "C001";
        }else{
           int maxId=  Integer.parseInt(lastCustomerId.replace("C",""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "C00" + maxId;
            } else if (maxId < 100) {
                id = "C0" + maxId;
            } else {
                id = "C" + maxId;
            }
            return id;
        }
    }

    public static List<CustomerTM> getAllCustomers(){
        List<Customer> allCustomers = new CustomerDAO().findAllCustomers();
        List<CustomerTM> customerTMS = new ArrayList<>();

        for (Customer customerTM : allCustomers) {
            customerTMS.add(new CustomerTM(customerTM.getId(),customerTM.getName(),customerTM.getAddress()));
        }
        return customerTMS;
    }

    public static boolean saveCustomer(String id, String name, String address){
        return new CustomerDAO().addCustomer(new Customer(id, name, address));
    }

    public static boolean deleteCustomer(String customerId){
        return new CustomerDAO().deleteCustomer(customerId);
    }

    public static boolean updateCustomer(String name, String address, String customerId){
        return new CustomerDAO().updateCustomer(new Customer(name,address,customerId));
    }

    public static List<ItemTM> getAllItems(){
        List<Item> allItems = new ItemDAO().findAllItems();
        List<ItemTM> itemTMS = new ArrayList<>();

        for (Item allItem : allItems) {
            itemTMS.add(new ItemTM(allItem.getCode(),allItem.getDescription(),allItem.getUnitPrice().doubleValue(),allItem.getQtyOnHand()));
        }
        return itemTMS;
    }

    public static boolean saveItem(String code, String description, int qtyOnHand, BigDecimal unitPrice){
        return new ItemDAO().addItem(new Item(code,description,unitPrice,qtyOnHand));
    }

    public static boolean deleteItem(String itemCode){
        return new ItemDAO().deleteItem(itemCode);
    }

    public static boolean updateItem(String description, int qtyOnHand, BigDecimal unitPrice, String itemCode){
        return new ItemDAO().updateItem(new Item(itemCode,description,unitPrice,qtyOnHand));
    }

    public static boolean placeOrder(OrderTM order, List<OrderDetailTM> orderDetails){
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);


            boolean b = new OrderDAO().addOrder(new Order(order.getOrderId(), Date.valueOf(order.getOrderDate()), order.getCustomerId()));
            if(!b){
                connection.rollback();
                return false;
            }

            for (OrderDetailTM orderDetail : orderDetails) {


                b = new OrderDetailDAO().addOrderDetail(new OrderDetail(order.getOrderId(), orderDetail.getCode(), orderDetail.getQty(), BigDecimal.valueOf(orderDetail.getUnitPrice())));
                if(!b){
                    connection.rollback();
                    return false;
                }

                b = new ItemDAO().updateItem(new Item(orderDetail.getCode(), orderDetail.getDescription(), BigDecimal.valueOf(orderDetail.getUnitPrice()), orderDetail.getQty()));
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

    }

    public static String autoGeneratePlaceOrderID(){

        String oldID = new OrderDetailDAO().getLastOrderDetailID();
        oldID = oldID.substring(2, 5);

        int newID = Integer.parseInt(oldID) + 1;

        if (newID < 10) {
            return  "OD00" + newID;
        } else if (newID < 100) {
            return  "OD0" + newID;
        } else {
            return  "OD" + newID;
        }
    }                                                         
}
