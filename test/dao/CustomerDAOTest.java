package dao;

import dao.custom.QueryDAO;
import dao.custom.impl.QueryDAOImpl;
import entity.CustomEntity;
import entity.Customer;

import java.util.List;

public class CustomerDAOTest {
    public static void main(String[] args) {
        QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
        CustomEntity o001 = queryDAO.getOrderDetail("OD001");
        System.out.println(o001.getCustomerName());
        System.out.println(o001.getOrderID());
        System.out.println(o001.getOrderDate());
        System.out.println();
        CustomEntity o002 = queryDAO.getOrderDetail2("OD001");
        System.out.println(o002.getCustomerID());
        System.out.println(o002.getCustomerName());
        System.out.println(o002.getOrderID());
    }
}
