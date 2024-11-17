package com.menekseyuncu.storemanagement.cart.model.mapper;

import com.menekseyuncu.storemanagement.cart.model.domain.Cart;
import com.menekseyuncu.storemanagement.cart.model.entity.CartEntity;
import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CartEntityToDomainMapper extends BaseMapper<CartEntity, Cart> {

    CartEntityToDomainMapper INSTANCE = Mappers.getMapper(CartEntityToDomainMapper.class);
}
