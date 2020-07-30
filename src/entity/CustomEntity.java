package entity;

import java.sql.Date;

public class CustomEntity implements SuperEntity {
    private String orderID;
    private Date orderDate;
    private String customerID;
    private String customerName;
    double total;

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

    public CustomEntity(String orderID, Date orderDate, String customerID, String customerName, double total) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.customerName = customerName;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    @Override
    public String toString() {
        return "CustomEntity{" +
                "orderID='" + orderID + '\'' +
                ", orderDate=" + orderDate +
                ", customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", total=" + total +
                '}';
    }
}
