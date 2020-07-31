package business.custom;

import business.SuperBO;
import util.ItemTM;

import java.math.BigDecimal;
import java.util.List;

public interface ItemBO extends SuperBO {
    public String getNewItemCode()throws Exception;

    public List<ItemTM> getAllItems()throws Exception;

    public boolean saveItem(String code, String description, int qtyOnHand, BigDecimal unitPrice)throws Exception;

    public boolean deleteItem(String itemCode)throws Exception;

    public boolean updateItem(String description, int qtyOnHand, BigDecimal unitPrice, String itemCode)throws Exception;
}
