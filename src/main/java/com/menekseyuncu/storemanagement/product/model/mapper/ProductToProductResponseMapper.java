package com.menekseyuncu.storemanagement.product.model.mapper;

import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import com.menekseyuncu.storemanagement.product.controller.response.ProductResponse;
import com.menekseyuncu.storemanagement.product.model.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductToProductResponseMapper extends BaseMapper<Product, ProductResponse> {

    ProductToProductResponseMapper INSTANCE = Mappers.getMapper(ProductToProductResponseMapper.class);
}