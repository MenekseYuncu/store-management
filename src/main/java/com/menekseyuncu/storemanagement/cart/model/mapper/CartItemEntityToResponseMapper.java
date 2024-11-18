package com.menekseyuncu.storemanagement.cart.model.mapper;

import com.menekseyuncu.storemanagement.cart.controller.response.CartItemResponse;
import com.menekseyuncu.storemanagement.cart.model.entity.CartItemEntity;
import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CartItemEntityToResponseMapper extends BaseMapper<CartItemEntity, CartItemResponse> {

    CartItemEntityToResponseMapper INSTANCE = Mappers.getMapper(CartItemEntityToResponseMapper.class);
}
