package com.dep.sula.dao.custom.impl;

import com.dep.sula.dao.CrudUtil;
import com.dep.sula.dao.custom.CustomerDAO;
import com.dep.sula.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public CustomerDAOImpl() {
    }

    public int saveCustomer(String id,String nm,String add) throws SQLException {
        String sql="insert into customer values(?,?,?)";
        int i= CrudUtil.execute(sql,id,nm,add);

        return i;

    }

    public int deleteCustomer(String id) throws SQLException {

        return CrudUtil.execute("DELETE from customer where id=?",id);
    }

    public int updateCustomer(String id, String nm, String add) throws SQLException {

        String sql="update customer set name=?, address=? where customerId=?";
        return CrudUtil.execute(sql,nm,add,id);
    }

    public ArrayList<Customer> loadCustomer() throws SQLException {

        ArrayList<Customer> customers=new ArrayList<>();

        ResultSet rst=CrudUtil.execute("select * FROM customer");
        while (rst.next()){
            String id = rst.getString(1);
            String nm = rst.getString(2);
            String add = rst.getString(3);

            customers.add(new Customer(id,nm,add));
        }

        for (Customer c:customers) {
            System.out.println(c);
        }

        return customers;
    }

    public String getLastCustomerId() throws SQLException {
        ResultSet resultSet=CrudUtil.execute("select * FROM customer");
        boolean b=resultSet.last();

        return resultSet.getString(1);
    }


    @Override
    public List<Customer> findAll() throws Exception {
        ArrayList<Customer> customers=new ArrayList<>();

        ResultSet rst=CrudUtil.execute("select * FROM customer");
        while (rst.next()){
            String id = rst.getString(1);
            String nm = rst.getString(2);
            String add = rst.getString(3);

            customers.add(new Customer(id,nm,add));
        }

        for (Customer c:customers) {
            System.out.println(c);
        }

        return customers;
    }

    @Override
    public Customer find(String s) throws Exception {
        return null;
    }

    @Override
    public int save(Customer entity) throws Exception {
        String sql="insert into customer values(?,?,?)";
        int i= CrudUtil.execute(sql,entity.getId(),entity.getName(),entity.getAddress());

        return i;
    }

    @Override
    public int update(Customer entity) throws Exception {
        String sql="update customer set name=?, address=? where customerId=?";
        return CrudUtil.execute(sql,entity.getName(),entity.getAddress(),entity.getId());
    }

    @Override
    public int delete(String s) throws Exception {
         return CrudUtil.execute("DELETE from customer where id=?",s);
    }
}
