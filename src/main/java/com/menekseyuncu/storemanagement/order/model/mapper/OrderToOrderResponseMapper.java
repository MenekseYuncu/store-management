package com.menekseyuncu.storemanagement.order.model.mapper;


import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import com.menekseyuncu.storemanagement.order.controller.response.OrderResponse;
import com.menekseyuncu.storemanagement.order.model.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderToOrderResponseMapper extends BaseMapper<Order, OrderResponse> {

    OrderToOrderResponseMapper INSTANCE = Mappers.getMapper(OrderToOrderResponseMapper.class);
}
