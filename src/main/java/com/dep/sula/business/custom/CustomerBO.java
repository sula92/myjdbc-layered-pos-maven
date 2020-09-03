package com.dep.sula.business.custom;

import com.dep.sula.business.SuperBO;
import com.dep.sula.dto.CustomerDTO;

import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public int saveCustomer(CustomerDTO customerDTO);

    public int deleteCustomer(String id);

    public int updateCustomer(CustomerDTO customerDTO);

    public ArrayList<CustomerDTO> findAllCustomers();

    public String getLastCustomerId();

    public boolean getAllCustomerID();

}
