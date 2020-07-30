package dao;

import entity.Item;

import java.util.List;

public interface ItemDAO extends SuperDAO <Item , String> {
    public String getLastItemID();
}
