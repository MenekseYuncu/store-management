package com.menekseyuncu.storemanagement.product.model.mapper;

import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import com.menekseyuncu.storemanagement.product.controller.request.ProductCreateRequest;
import com.menekseyuncu.storemanagement.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ProductCreateRequestToProductEntityMapper extends BaseMapper<ProductCreateRequest, ProductEntity> {

    ProductCreateRequestToProductEntityMapper INSTANCE = Mappers.getMapper(ProductCreateRequestToProductEntityMapper.class);
}
