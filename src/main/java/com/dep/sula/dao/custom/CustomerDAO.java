package com.dep.sula.dao.custom;

import com.dep.sula.dao.CrudDAO;
import com.dep.sula.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer,String> {

    public abstract String getLastCustomerId() throws SQLException;

}
