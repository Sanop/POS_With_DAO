package dao;

import dao.custom.QueryDAO;
import dao.custom.impl.QueryDAOImpl;
import entity.CustomEntity;
import entity.Customer;

import java.util.List;

public class CustomerDAOTest {
    public static void main(String[] args) {

        QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);

        try {
            CustomEntity o001 = queryDAO.getOrderDetail("OD001");
            System.out.println(o001.getCustomerName());
            System.out.println(o001.getOrderID());
            System.out.println(o001.getOrderDate());
            System.out.println();
            CustomEntity o002 = queryDAO.getOrderDetail2("OD001");
            System.out.println(o002.getCustomerID());
            System.out.println(o002.getCustomerName());
            System.out.println(o002.getOrderID());
            System.out.println();


            List<CustomEntity> allOrderDetail = queryDAO.getAllOrderDetail();
            for (CustomEntity customEntity : allOrderDetail) {
                System.out.println(customEntity);
            }

            System.out.println();

            allOrderDetail = queryDAO.SearchAllOrderDetail("C001");
            for (CustomEntity customEntity : allOrderDetail) {
                System.out.println(customEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
