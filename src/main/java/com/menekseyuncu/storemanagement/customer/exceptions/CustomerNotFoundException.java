package com.menekseyuncu.storemanagement.customer.exceptions;

import com.menekseyuncu.storemanagement.common.exceptions.ResourceNotFoundException;

import java.io.Serial;

public class CustomerNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = 5783789473698831394L;

    public CustomerNotFoundException() {
        super("Customer not found");
    }

}
