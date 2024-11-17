package com.menekseyuncu.storemanagement.customer;

import com.menekseyuncu.storemanagement.common.exceptions.ResourceNotFoundException;

import java.io.Serial;

public class CustomerNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = 5783789473698831394L;

    public CustomerNotFoundException(String email) {
        super("Customer not found with email " + email);
    }

}
