package com.menekseyuncu.storemanagement.product.controller;

import com.menekseyuncu.storemanagement.common.responses.BaseResponse;
import com.menekseyuncu.storemanagement.product.controller.request.ProductCreateRequest;
import com.menekseyuncu.storemanagement.product.controller.request.ProductUpdateRequest;
import com.menekseyuncu.storemanagement.product.controller.response.ProductResponse;
import com.menekseyuncu.storemanagement.product.model.domain.Product;
import com.menekseyuncu.storemanagement.product.model.mapper.ProductToProductResponseMapper;
import com.menekseyuncu.storemanagement.product.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
class ProductController {

    private static final ProductToProductResponseMapper productToResponseMapper = ProductToProductResponseMapper.INSTANCE;
    private final ProductService productService;

    @GetMapping("/{id}")
    public BaseResponse<ProductResponse> getProductById(
            @PathVariable @Positive Long id
    ) {
        Product product = productService.getProductById(id);
        ProductResponse response = productToResponseMapper.map(product);
        return BaseResponse.successOf(response);
    }

    @GetMapping
    public BaseResponse<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return BaseResponse.successOf(products);
    }

    @PostMapping
    public BaseResponse<Void> createProduct(
            @RequestBody @Valid ProductCreateRequest productCreateRequest
    ) {
        productService.createProduct(productCreateRequest);
        return BaseResponse.SUCCESS;
    }

    @PutMapping("/{id}")
    public BaseResponse<Void> updateProduct(
            @PathVariable @Positive Long id,
            @Valid @RequestBody ProductUpdateRequest productUpdateRequest
    ) {
        productService.updateProduct(id, productUpdateRequest);
        return BaseResponse.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteProduct(
            @PathVariable @Positive Long id
    ) {
        productService.deleteProduct(id);
        return BaseResponse.SUCCESS;
    }
}
