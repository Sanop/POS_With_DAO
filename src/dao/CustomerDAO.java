package dao;

import com.sun.xml.internal.bind.v2.model.core.ID;
import entity.Customer;

import java.util.List;

public interface CustomerDAO extends SuperDAO <Customer , String > {
    public String getLastCustomerID();
}
