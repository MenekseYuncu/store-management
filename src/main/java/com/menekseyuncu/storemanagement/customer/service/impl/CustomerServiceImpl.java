package com.menekseyuncu.storemanagement.customer.service.impl;


import com.menekseyuncu.storemanagement.customer.controller.request.CustomerGetByEmailRequest;
import com.menekseyuncu.storemanagement.customer.controller.response.CustomerResponse;
import com.menekseyuncu.storemanagement.customer.exceptions.CustomerAlreadyDeletedException;
import com.menekseyuncu.storemanagement.customer.exceptions.CustomerNotFoundException;
import com.menekseyuncu.storemanagement.customer.model.domain.Customer;
import com.menekseyuncu.storemanagement.customer.model.entity.CustomerEntity;
import com.menekseyuncu.storemanagement.customer.model.mapper.CustomerEntityToDomainMapper;
import com.menekseyuncu.storemanagement.customer.model.mapper.CustomerToCustomerResponseMapper;
import com.menekseyuncu.storemanagement.customer.repository.CustomerRepository;
import com.menekseyuncu.storemanagement.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class CustomerServiceImpl implements CustomerService {

    private static final CustomerEntityToDomainMapper customerEntityToDomainMapper = CustomerEntityToDomainMapper.INSTANCE;
    private static final CustomerToCustomerResponseMapper customerToCustomerResponseMapper = CustomerToCustomerResponseMapper.INSTANCE;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse getCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(CustomerNotFoundException::new);

        Customer customer = customerEntityToDomainMapper.map(customerEntity);

        return customerToCustomerResponseMapper.map(customer);
    }

    @Override
    public CustomerResponse getCustomerByEmail(CustomerGetByEmailRequest customerGetByEmailRequest) {
        CustomerEntity customerEntity = customerRepository.findByEmailAndDeletedAtIsNull(customerGetByEmailRequest.email())
                .orElseThrow(CustomerNotFoundException::new);

        Customer customer = customerEntityToDomainMapper.map(customerEntity);

        return customerToCustomerResponseMapper.map(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAllByDeletedAtIsNull().stream()
                .map(customer -> CustomerResponse.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .email(customer.getEmail())
                        .address(customer.getAddress())
                        .createdAt(customer.getCreatedAt())
                        .updatedAt(customer.getUpdatedAt())
                        .build())
                .toList();
    }

    @Override
    public void deleteCustomer(Long id) {
        CustomerEntity customerEntity = customerRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(CustomerNotFoundException::new);

        if (customerEntity.isDeleted()) {
            throw new CustomerAlreadyDeletedException();
        }

        customerEntity.softDelete();
        customerRepository.save(customerEntity);
    }
}
