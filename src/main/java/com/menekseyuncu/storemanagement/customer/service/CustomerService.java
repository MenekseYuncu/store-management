package com.menekseyuncu.storemanagement.customer.service;

import com.menekseyuncu.storemanagement.customer.controller.request.CustomerGetByEmailRequest;
import com.menekseyuncu.storemanagement.customer.controller.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse getCustomerById(Long id);

    CustomerResponse getCustomerByEmail(CustomerGetByEmailRequest customerGetByEmailRequest);

    List<CustomerResponse> getAllCustomers();

    void deleteCustomer(Long id);

}
