package com.dep.sula.dao.custom;

import com.dep.sula.dao.CrudDAO;
import com.dep.sula.entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item,String> {

    public int updateQty(int qty, String icode) throws SQLException;

    public abstract String getLastItemId() throws SQLException;

}
