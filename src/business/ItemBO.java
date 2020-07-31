package business;

import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ItemDAO;
import entity.Item;
import util.ItemTM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemBO {
    public static String getNewItemCode(){
        try {
            ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
            String lastItemCode = itemDAO.getLastItemID();
            if (lastItemCode == null){
                return "I001";
            }else{
                int maxId=  Integer.parseInt(lastItemCode.replace("I",""));
                maxId = maxId + 1;
                String id = "";
                if (maxId < 10) {
                    id = "I00" + maxId;
                } else if (maxId < 100) {
                    id = "I0" + maxId;
                } else {
                    id = "I" + maxId;
                }
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ItemTM> getAllItems(){
        try {
            ItemDAO itemDAO =DAOFactory.getInstance().getDAO(DAOType.ITEM);
            List<Item> allItems = itemDAO.findAll();
            List<ItemTM> itemTMS = new ArrayList<>();

            for (Item allItem : allItems) {
                itemTMS.add(new ItemTM(allItem.getCode(),allItem.getDescription(),allItem.getUnitPrice().doubleValue(),allItem.getQtyOnHand()));
            }
            return itemTMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean saveItem(String code, String description, int qtyOnHand, BigDecimal unitPrice){
        try {
            ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
            return itemDAO.add(new Item(code,description,unitPrice,qtyOnHand));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteItem(String itemCode){
        try {
            ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
            return itemDAO.delete(itemCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateItem(String description, int qtyOnHand, BigDecimal unitPrice, String itemCode){
        try {
            ItemDAO itemDAO =DAOFactory.getInstance().getDAO(DAOType.ITEM);
            return itemDAO.update(new Item(itemCode,description,unitPrice,qtyOnHand));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
