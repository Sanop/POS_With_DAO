package business.custom.impl;

import business.custom.CustomerBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.CustomerDAO;
import entity.Customer;
import util.CustomerTM;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    public String getNewCustomerId()throws Exception{
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
    }

    public List<CustomerTM> getAllCustomers()throws Exception{

            CustomerDAO customerDAO =DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            List<Customer> allCustomers = customerDAO.findAll() ;
            List<CustomerTM> customerTMS = new ArrayList<>();

            for (Customer customerTM : allCustomers) {
                customerTMS.add(new CustomerTM(customerTM.getId(),customerTM.getName(),customerTM.getAddress()));
            }
            return customerTMS;
    }

    public boolean saveCustomer(String id, String name, String address)throws Exception{

            CustomerDAO customerDAO =DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            return customerDAO.add(new Customer(id, name, address));
    }

    public boolean deleteCustomer(String customerId)throws Exception{
            CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            return customerDAO.delete(customerId);
    }

    public boolean updateCustomer(String name, String address, String customerId)throws Exception{
            CustomerDAO customerDAO =DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            return customerDAO.update(new Customer(name,address,customerId));
    }

}
