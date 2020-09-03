package com.dep.sula.dao.custom.impl;

import com.dep.sula.dao.CrudUtil;
import com.dep.sula.dao.custom.OrderDetailDAO;
import com.dep.sula.entity.OrderDetail;

import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public List<OrderDetail> findAll() throws Exception {
        return null;
    }

    @Override
    public OrderDetail find(String s) throws Exception {
        return null;
    }

    @Override
    public int save(OrderDetail entity) throws Exception {
        return CrudUtil.execute("INSERT INTO OrderDetail VALUES (?,?,?,?)",entity.getOrderDetailPk().getOrderID(),entity.getOrderDetailPk().getItemCode(),entity.getQty(),entity.getUnitPrice());
    }

    @Override
    public int update(OrderDetail entity) throws Exception {
        return 0;
    }

    @Override
    public int delete(String s) throws Exception {
        return 0;
    }
}
