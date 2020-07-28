package entity;

public class OrderDetailPK {
    private String orderID;
    private String itemCode;

    public OrderDetailPK() {
    }

    public OrderDetailPK(String orderID, String itemCode) {
        this.setOrderID(orderID);
        this.setItemCode(itemCode);
    }


    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public String toString() {
        return "OrderDetailPK{" +
                "orderID='" + orderID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                '}';
    }
}
