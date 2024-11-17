package com.menekseyuncu.storemanagement.cart.model.mapper;

import com.menekseyuncu.storemanagement.cart.controller.response.CartResponse;
import com.menekseyuncu.storemanagement.cart.model.domain.Cart;
import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CartToCartResponseMapper extends BaseMapper<Cart, CartResponse> {

    CartToCartResponseMapper INSTANCE = Mappers.getMapper(CartToCartResponseMapper.class);
}
