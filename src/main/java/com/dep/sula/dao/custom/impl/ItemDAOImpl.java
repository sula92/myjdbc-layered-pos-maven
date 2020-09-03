package com.dep.sula.dao.custom.impl;

import com.dep.sula.dao.CrudUtil;
import com.dep.sula.dao.custom.ItemDAO;
import com.dep.sula.db.DBConnection;
import com.dep.sula.entity.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    PreparedStatement preparedStatement;
    Connection connection= DBConnection.getInstance().getConnection();

    public int saveItem(String code, String des, String up, String qty) throws SQLException {
        preparedStatement=connection.prepareStatement("insert into item values (?,?,?,?)");
        preparedStatement.setString(1,code);
        preparedStatement.setString(2,des);
        preparedStatement.setObject(3,up);
        preparedStatement.setString(4,qty);
        return preparedStatement.executeUpdate();
    }

    public int updateItem(String code, String des, String up, String qty) throws SQLException {
        preparedStatement=connection.prepareStatement("update item set unitPrice=?, description=?, qtyOnHand=? where code=?");
        preparedStatement.setString(1,up);
        preparedStatement.setString(2,des);
        preparedStatement.setString(3,qty);
        preparedStatement.setString(4,code);

        return preparedStatement.executeUpdate();
    }

    public ArrayList<Item> loadItems() throws SQLException {
        ArrayList<Item> items=new ArrayList<>();
        preparedStatement=connection.prepareStatement("SELECT * FROM item");
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            String code=resultSet.getString(1);
            String des=resultSet.getString(2);
            String up=resultSet.getString(3);
            String qty=resultSet.getString(4);
            items.add(new Item(code,des,up,qty));
        }
        return items;
    }

    public int deleteItem(String code) throws SQLException {
        preparedStatement=connection.prepareStatement("DELETE from item where code=?");
        preparedStatement.setString(1,code);
        return preparedStatement.executeUpdate();
    }

    public String getLastItemCode() throws SQLException {
        preparedStatement=connection.prepareStatement("SELECT * FROM item");
        ResultSet rst=preparedStatement.executeQuery();
        rst.last();
        return rst.getString(1);
    }

    @Override
    public List<Item> findAll() throws SQLException {
        List<Item> items=new ArrayList<>();
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM item");
        while (resultSet.next()){
            String code=resultSet.getString(1);
            String des=resultSet.getString(2);
            String qty=resultSet.getString(4);
            String up=resultSet.getString(3);

            items.add(new Item(code,des,qty,up));
        }
        return items;
    }

    @Override
    public Item find(String s) throws Exception {
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM item WHERE code=?",s);
        resultSet.next();
        String code=resultSet.getString(1);
        String des=resultSet.getString(2);
        String qty=String.valueOf(resultSet.getObject(4));
        String up=String.valueOf(resultSet.getObject(3));

        return new Item(code,des,qty,up);
    }

    @Override
    public int save(Item entity) throws Exception {
        int i= CrudUtil.execute("insert into item values (?,?,?,?)",entity.getItemCode(),entity.getDescription(),entity.getUprice(),entity.getQty());
        return i;
    }

    @Override
    public int update(Item entity) throws Exception {
        int i=CrudUtil.execute("update item set  description=?, unitPrice=?, qtyOnHand=? where code=?",entity.getDescription(),entity.getUprice(),entity.getQty(),entity.getItemCode());
        return i;
    }

    @Override
    public int delete(String s) throws Exception {
        int i=CrudUtil.execute("DELETE from item where code=?",s);
        return i;
    }


    @Override
    public int updateQty(int qty,String icode) throws SQLException {
        int i=CrudUtil.execute("update item set qtyOnHand=? where code=?",qty,icode);
        return i;
    }

    @Override
    public String getLastItemId() throws SQLException {
        ResultSet r= CrudUtil.execute("SELECT code FROM item ORDER BY code DESC LIMIT 1");
        r.next();
        return r.getString(1);

    }
}
