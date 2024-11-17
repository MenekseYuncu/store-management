package com.menekseyuncu.storemanagement.product.service.impl;

import com.menekseyuncu.storemanagement.product.controller.request.ProductCreateRequest;
import com.menekseyuncu.storemanagement.product.controller.request.ProductUpdateRequest;
import com.menekseyuncu.storemanagement.product.controller.response.ProductResponse;
import com.menekseyuncu.storemanagement.product.exceptions.ProductNotFoundException;
import com.menekseyuncu.storemanagement.product.model.domain.Product;
import com.menekseyuncu.storemanagement.product.model.entity.ProductEntity;
import com.menekseyuncu.storemanagement.product.model.mapper.ProductCreateRequestToProductEntityMapper;
import com.menekseyuncu.storemanagement.product.model.mapper.ProductEntityToDomainMapper;
import com.menekseyuncu.storemanagement.product.model.mapper.ProductUpdateRequestToProductEntityMapper;
import com.menekseyuncu.storemanagement.product.repository.ProductRepository;
import com.menekseyuncu.storemanagement.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ProductServiceImpl implements ProductService {

    private static final ProductUpdateRequestToProductEntityMapper productUpdateRequestToProductEntity = ProductUpdateRequestToProductEntityMapper.INSTANCE;
    private static final ProductCreateRequestToProductEntityMapper productCreateRequestToEntityMapper = ProductCreateRequestToProductEntityMapper.INSTANCE;
    private static final ProductEntityToDomainMapper productEntityToDomain = ProductEntityToDomainMapper.INSTANCE;
    private final ProductRepository productRepository;


    @Override
    public void createProduct(ProductCreateRequest productRequest) {
        ProductEntity productEntity = productCreateRequestToEntityMapper.map(productRequest);

        productRepository.save(productEntity);
    }

    @Override
    public Product getProductById(Long id) {
        ProductEntity productEntity = productRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(ProductNotFoundException::new);

        return productEntityToDomain.map(productEntity);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAllByDeletedAtIsNull().stream()
                .map(product -> ProductResponse.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .build())
                .toList();
    }

    @Override
    public void updateProduct(Long id, ProductUpdateRequest productRequest) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        ProductEntity updateEntity = productUpdateRequestToProductEntity.map(productRequest);

        productEntity.setName(updateEntity.getName());
        productEntity.setPrice(updateEntity.getPrice());
        productEntity.setStock(updateEntity.getStock());

        productRepository.save(productEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        product.softDelete();
        productRepository.save(product);
    }
}
