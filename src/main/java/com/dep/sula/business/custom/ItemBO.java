package com.dep.sula.business.custom;

import com.dep.sula.business.SuperBO;
import com.dep.sula.dto.ItemDTO;

import java.util.ArrayList;

public interface ItemBO extends SuperBO {

    public int saveItem(ItemDTO itemDTO);

    public int deleteItem(String id);

    public int updateItem(ItemDTO itemDTO);

    public ArrayList<ItemDTO> findAllItems();

    public String getLastItemId();

    public boolean getAllItemID();

}
