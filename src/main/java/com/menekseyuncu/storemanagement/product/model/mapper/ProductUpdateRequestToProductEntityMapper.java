package com.menekseyuncu.storemanagement.product.model.mapper;

import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import com.menekseyuncu.storemanagement.product.controller.request.ProductUpdateRequest;
import com.menekseyuncu.storemanagement.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductUpdateRequestToProductEntityMapper extends BaseMapper<ProductUpdateRequest, ProductEntity> {

    ProductUpdateRequestToProductEntityMapper INSTANCE = Mappers.getMapper(ProductUpdateRequestToProductEntityMapper.class);
}