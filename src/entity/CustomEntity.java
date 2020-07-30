package entity;

import java.sql.Date;

public class CustomEntity implements SuperEntity {
    private String orderID;
    private String customerName;
    private Date orderDate;
    private String customerID;

    public CustomEntity() {
    }

    public CustomEntity(String orderID, String customerName, Date orderDate) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.orderDate = orderDate;
    }

    public CustomEntity(String orderID, String customerName, String customerID) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.customerID = customerID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}
