package com.dep.sula.dao.custom.impl;

import com.dep.sula.dao.CrudUtil;
import com.dep.sula.dao.custom.OrderDAO;
import com.dep.sula.entity.Order;

import java.time.LocalDate;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public List<Order> findAll() throws Exception {
        return null;
    }

    @Override
    public Order find(String s) throws Exception {
        return null;
    }

    @Override
    public int save(Order entity) throws Exception {
        return CrudUtil.execute("INSERT INTO `Order` VALUES (?,?,?)",entity.getOrderId(), LocalDate.now(),entity.getCustomerId());
    }

    @Override
    public int update(Order entity) throws Exception {
        return 0;
    }

    @Override
    public int delete(String s) throws Exception {
        return 0;
    }


}
