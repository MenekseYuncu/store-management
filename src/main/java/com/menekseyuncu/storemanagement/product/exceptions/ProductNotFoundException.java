package com.menekseyuncu.storemanagement.product.exceptions;

import com.menekseyuncu.storemanagement.common.exceptions.ResourceNotFoundException;

import java.io.Serial;

public class ProductNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = 8314561727319843240L;

    public ProductNotFoundException() {
        super("Product not found !!");
    }
}