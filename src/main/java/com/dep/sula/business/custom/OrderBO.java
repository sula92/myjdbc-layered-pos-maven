package com.dep.sula.business.custom;

import com.dep.sula.business.SuperBO;
import com.dep.sula.dto.OrderDTO;
import com.dep.sula.dto.SearchOrderDTO;

import java.util.ArrayList;

public interface OrderBO extends SuperBO {

    public int placeOrder(OrderDTO order) throws Exception;

    public ArrayList<SearchOrderDTO> getAllOrderInformation();

}
