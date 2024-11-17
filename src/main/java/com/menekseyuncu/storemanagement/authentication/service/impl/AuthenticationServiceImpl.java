package com.menekseyuncu.storemanagement.authentication.service.impl;

import com.menekseyuncu.storemanagement.authentication.controller.request.ChangePasswordRequest;
import com.menekseyuncu.storemanagement.authentication.controller.request.LoginRequest;
import com.menekseyuncu.storemanagement.authentication.exception.AuthenticationException;
import com.menekseyuncu.storemanagement.authentication.exception.EmailAlreadyExistException;
import com.menekseyuncu.storemanagement.authentication.service.AuthenticationService;
import com.menekseyuncu.storemanagement.customer.CustomerNotFoundException;
import com.menekseyuncu.storemanagement.customer.controller.request.CustomerCreateRequest;
import com.menekseyuncu.storemanagement.customer.model.entity.CustomerEntity;
import com.menekseyuncu.storemanagement.customer.model.mapper.CustomerCreateRequestToCustomerEntityMapper;
import com.menekseyuncu.storemanagement.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final CustomerCreateRequestToCustomerEntityMapper customerCreateRequestToEntityMapper = CustomerCreateRequestToCustomerEntityMapper.INSTANCE;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(CustomerCreateRequest customerCreateRequest) {

        if (customerRepository.findByEmailAndDeletedAtIsNull(customerCreateRequest.email()).isPresent()) {
            throw new EmailAlreadyExistException();
        }

        String encodedPassword = passwordEncoder.encode(customerCreateRequest.password());

        CustomerEntity customer = customerCreateRequestToEntityMapper.map(customerCreateRequest);
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        String email = loginRequest.email();
        String password = loginRequest.password();

        CustomerEntity customer = customerRepository.findByEmailAndDeletedAtIsNull(email)
                .orElseThrow(() -> new AuthenticationException("Invalid email or password"));

        if (!passwordEncoder.matches(password, customer.getPassword())) {
            throw new AuthenticationException("Invalid email or password");
        }
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        CustomerEntity customer = customerRepository.findByEmailAndDeletedAtIsNull(changePasswordRequest.email())
                .orElseThrow(() -> new CustomerNotFoundException(changePasswordRequest.email()));

        if (!passwordEncoder.matches(changePasswordRequest.oldPassword(), customer.getPassword())) {
            throw new AuthenticationException("Old password is incorrect");
        }

        customer.setPassword(passwordEncoder.encode(changePasswordRequest.newPassword()));
        customerRepository.save(customer);
    }
}
