package com.menekseyuncu.storemanagement.cart.model.mapper;

import com.menekseyuncu.storemanagement.cart.controller.request.CartItemRequest;
import com.menekseyuncu.storemanagement.cart.model.entity.CartItemEntity;
import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartItemRequestToCartItemEntityMapper extends BaseMapper<CartItemRequest, CartItemEntity> {

    CartItemRequestToCartItemEntityMapper INSTANCE = Mappers.getMapper(CartItemRequestToCartItemEntityMapper.class);
}
