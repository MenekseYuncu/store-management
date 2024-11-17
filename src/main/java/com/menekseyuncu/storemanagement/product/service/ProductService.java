package com.menekseyuncu.storemanagement.product.service;

import com.menekseyuncu.storemanagement.product.controller.request.ProductCreateRequest;
import com.menekseyuncu.storemanagement.product.controller.request.ProductUpdateRequest;
import com.menekseyuncu.storemanagement.product.controller.response.ProductResponse;
import com.menekseyuncu.storemanagement.product.model.domain.Product;

import java.util.List;

public interface ProductService {

    void createProduct(ProductCreateRequest productRequest);

    Product getProductById(Long id);

    List<ProductResponse> getAllProducts();

    void updateProduct(Long id, ProductUpdateRequest productRequest);

    void deleteProduct(Long id);
}
