package business.custom;

import business.SuperBO;
import util.OrderDetailTM;
import util.OrderTM;

import java.util.List;

public interface OrderBO extends SuperBO {

    public boolean placeOrder(OrderTM order, List<OrderDetailTM> orderDetails)throws Exception;

    public String autoGeneratePlaceOrderID()throws Exception;
}
