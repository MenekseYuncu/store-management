package com.menekseyuncu.storemanagement.order.model.mapper;


import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import com.menekseyuncu.storemanagement.order.controller.request.OrderRequest;
import com.menekseyuncu.storemanagement.order.model.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderRequestToOrderEntityMapper extends BaseMapper<OrderRequest, OrderEntity> {

    OrderRequestToOrderEntityMapper INSTANCE = Mappers.getMapper(OrderRequestToOrderEntityMapper.class);
}
