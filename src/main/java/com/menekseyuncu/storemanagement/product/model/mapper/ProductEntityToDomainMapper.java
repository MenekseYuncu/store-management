package com.menekseyuncu.storemanagement.product.model.mapper;

import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import com.menekseyuncu.storemanagement.product.model.domain.Product;
import com.menekseyuncu.storemanagement.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductEntityToDomainMapper extends BaseMapper<ProductEntity, Product> {

    ProductEntityToDomainMapper INSTANCE = Mappers.getMapper(ProductEntityToDomainMapper.class);

}