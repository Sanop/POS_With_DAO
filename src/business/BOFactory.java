package business;

import business.custom.ItemBO;
import business.custom.impl.CustomerBOImpl;
import business.custom.impl.ItemBOImpl;
import business.custom.impl.OrderBOImpl;
import db.DBConnection;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boType){
        switch (boType){
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
            default:
                return null;
        }
    }
}
