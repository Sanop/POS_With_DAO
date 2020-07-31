package business;

import dao.DAOFactory;
import dao.DAOType;
import dao.custom.CustomerDAO;
import entity.Customer;
import util.CustomerTM;

import java.util.ArrayList;
import java.util.List;

public class CustomerBO {
    public static String getNewCustomerId(){
        try {
            CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            String lastCustomerId = customerDAO.getLastCustomerID();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<CustomerTM> getAllCustomers(){
        try {
            CustomerDAO customerDAO =DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            List<Customer> allCustomers = customerDAO.findAll() ;
            List<CustomerTM> customerTMS = new ArrayList<>();

            for (Customer customerTM : allCustomers) {
                customerTMS.add(new CustomerTM(customerTM.getId(),customerTM.getName(),customerTM.getAddress()));
            }
            return customerTMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean saveCustomer(String id, String name, String address){
        try {
            CustomerDAO customerDAO =DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            return customerDAO.add(new Customer(id, name, address));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteCustomer(String customerId){
        try {
            CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            return customerDAO.delete(customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateCustomer(String name, String address, String customerId){
        try {
            CustomerDAO customerDAO =DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            return customerDAO.update(new Customer(name,address,customerId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
