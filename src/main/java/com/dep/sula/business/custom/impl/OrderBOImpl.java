package com.dep.sula.business.custom.impl;

import com.dep.sula.business.custom.OrderBO;
import com.dep.sula.dao.DAOFactory;
import com.dep.sula.dao.DAOTypes;
import com.dep.sula.dao.custom.ItemDAO;
import com.dep.sula.dao.custom.OrderDAO;
import com.dep.sula.dao.custom.OrderDetailDAO;
import com.dep.sula.dao.custom.QueryDAO;
import com.dep.sula.db.DBConnection;
import com.dep.sula.dto.OrderDTO;
import com.dep.sula.dto.SearchOrderDTO;
import com.dep.sula.entity.CustomEntity;
import com.dep.sula.entity.Item;
import com.dep.sula.entity.Order;
import com.dep.sula.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO= DAOFactory.getInstance().getDAO(DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO=DAOFactory.getInstance().getDAO(DAOTypes.ORDERDETAIL);
    ItemDAO itemDAO=DAOFactory.getInstance().getDAO(DAOTypes.ITEM);
    QueryDAO queryDAO=DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public int placeOrder(OrderDTO order) throws Exception {
        connection.setAutoCommit(false);
        ArrayList<OrderDetail> orderDetails=new ArrayList<>();
        order.getOrderDetails().stream().forEach(oDTO -> {
            orderDetails.add(new OrderDetail(oDTO.getQty(),oDTO.getUnitPrice(),order.getOrderId(),oDTO.getCode()));
        });
        int i=orderDAO.save(new Order(order.getOrderId(),order.getOrderDate(),order.getCustomerId(),orderDetails));
        if(i>0){

            for (OrderDetail detail:orderDetails) {
                int j=orderDetailDAO.save(new OrderDetail(detail.getQty(),detail.getUnitPrice(),order.getOrderId(),detail.getOrderDetailPk().getItemCode()));
                Item item=itemDAO.find(detail.getOrderDetailPk().getItemCode());
                int oldQty=Integer.parseInt(item.getQty());
                int orderedQty=detail.getQty();
                String newQty=String.valueOf(oldQty-orderedQty);
                item.setQty(newQty);
                int k=itemDAO.update(item);
                if(k>0 && j>0){
                   connection.commit();
                   return 1;
                }
                else {
                    connection.rollback();
                    return 0;
                }
            }
        }
        else{
            connection.rollback();
            return 0;
        }
        return 1;
    }

    @Override
    public ArrayList<SearchOrderDTO> getAllOrderInformation() {
        ArrayList<SearchOrderDTO> searchOrderDTOS=new ArrayList<>();
        try {
            ArrayList<CustomEntity> customEntities=queryDAO.getALLOrderInformation();
            customEntities.stream().forEach(customEntity -> {
               searchOrderDTOS.add(new SearchOrderDTO(customEntity.getOrderId(),customEntity.getDate(),customEntity.getCustomerId(),customEntity.getCustomerName(),customEntity.getTotal()));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchOrderDTOS;
    }
}
