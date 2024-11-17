package com.menekseyuncu.storemanagement.customer.controller;


import com.menekseyuncu.storemanagement.authentication.service.AuthenticationService;
import com.menekseyuncu.storemanagement.common.responses.BaseResponse;
import com.menekseyuncu.storemanagement.customer.controller.request.CustomerCreateRequest;
import com.menekseyuncu.storemanagement.customer.controller.request.CustomerGetByEmailRequest;
import com.menekseyuncu.storemanagement.customer.controller.response.CustomerResponse;
import com.menekseyuncu.storemanagement.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final AuthenticationService authenticationService;

    @GetMapping("/{id}")
    public BaseResponse<CustomerResponse> getCustomerById(
            @PathVariable final Long id
    ) {
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        return BaseResponse.successOf(customerResponse);
    }

    @PostMapping("/email")
    public BaseResponse<CustomerResponse> getCustomerByEmail(
            @Valid @RequestBody final CustomerGetByEmailRequest customerGetByEmailRequest
    ) {
        CustomerResponse customerResponse = customerService.getCustomerByEmail(customerGetByEmailRequest);
        return BaseResponse.successOf(customerResponse);
    }

    @GetMapping("/customers")
    public BaseResponse<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = customerService.getAllCustomers();
        return BaseResponse.successOf(customers);
    }

    @PostMapping
    public BaseResponse<Void> addCustomer(
            @Valid @RequestBody final CustomerCreateRequest customerCreateRequest
    ) {
        authenticationService.register(customerCreateRequest);
        return BaseResponse.SUCCESS;
    }


    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteCustomer(
            @PathVariable final Long id) {
        customerService.deleteCustomer(id);
        return BaseResponse.SUCCESS;
    }
}
