package com.menekseyuncu.storemanagement.cart.model.mapper;

import com.menekseyuncu.storemanagement.cart.controller.request.CartCreateRequest;
import com.menekseyuncu.storemanagement.cart.model.entity.CartEntity;
import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartCreateRequestToEntityMapper extends BaseMapper<CartCreateRequest, CartEntity> {

    CartCreateRequestToEntityMapper INSTANCE = Mappers.getMapper(CartCreateRequestToEntityMapper.class);
}
