package com.dep.sula.business.custom.impl;

import com.dep.sula.business.custom.CustomerBO;
import com.dep.sula.dao.DAOFactory;
import com.dep.sula.dao.DAOTypes;
import com.dep.sula.dao.custom.CustomerDAO;
import com.dep.sula.dto.CustomerDTO;
import com.dep.sula.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO= DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);

    public int saveCustomer(CustomerDTO customerDTO) {
        Customer customer=new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
        try {
            int i=customerDAO.save(customer);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteCustomer(String id) {
        try {
            return customerDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateCustomer(CustomerDTO customerDTO) {
        try {
            customerDAO.update(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<CustomerDTO> findAllCustomers() {
        ArrayList<CustomerDTO> customerDTOS=new ArrayList<>();
        try {
            List<Customer> customers=customerDAO.findAll();
            customers.stream().forEach(customer -> {
                customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerDTOS;
    }

    @Override
    public String getLastCustomerId() {
        try {
            String id=customerDAO.getLastCustomerId();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean getAllCustomerID() {
        return false;
    }
}
