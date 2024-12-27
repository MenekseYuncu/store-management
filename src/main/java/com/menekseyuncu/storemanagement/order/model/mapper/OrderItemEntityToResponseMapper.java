package com.menekseyuncu.storemanagement.order.model.mapper;

import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import com.menekseyuncu.storemanagement.order.controller.response.OrderItemResponse;
import com.menekseyuncu.storemanagement.order.model.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderItemEntityToResponseMapper extends BaseMapper<OrderItemEntity, OrderItemResponse> {

    OrderItemEntityToResponseMapper INSTANCE = Mappers.getMapper(OrderItemEntityToResponseMapper.class);

}
