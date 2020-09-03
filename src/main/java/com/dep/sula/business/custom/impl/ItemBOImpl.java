package com.dep.sula.business.custom.impl;

import com.dep.sula.business.custom.ItemBO;
import com.dep.sula.dao.DAOFactory;
import com.dep.sula.dao.DAOTypes;
import com.dep.sula.dao.custom.ItemDAO;
import com.dep.sula.dto.ItemDTO;
import com.dep.sula.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO= DAOFactory.getInstance().getDAO(DAOTypes.ITEM);

    @Override
    public int saveItem(ItemDTO itemDTO) {
        try {
            int i=itemDAO.save(new Item(itemDTO.getItemCode(),itemDTO.getDescription(),itemDTO.getQty(),itemDTO.getUprice()));
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteItem(String id) {
        try {
            return itemDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
           return 0;
    }

    @Override
    public int updateItem(ItemDTO itemDTO) {
        try {
            int i=itemDAO.update(new Item(itemDTO.getItemCode(),itemDTO.getDescription(),itemDTO.getQty(),itemDTO.getUprice()));
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<ItemDTO> findAllItems() {
        ArrayList<ItemDTO> itemDTOS=new ArrayList<>();
        try {
            itemDAO.findAll().stream().forEach(item -> {
                itemDTOS.add(new ItemDTO(item.getItemCode(),item.getDescription(),item.getQty(),item.getUprice()));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemDTOS;
    }

    @Override
    public String getLastItemId() {
        String id= null;
        try {
            id = itemDAO.getLastItemId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean getAllItemID() {
        return false;
    }
}
