package com.menekseyuncu.storemanagement.order.model.mapper;

import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import com.menekseyuncu.storemanagement.order.model.domain.Order;
import com.menekseyuncu.storemanagement.order.model.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface OrderEntityToOrderMapper extends BaseMapper<OrderEntity, Order> {

    OrderEntityToOrderMapper INSTANCE = Mappers.getMapper(OrderEntityToOrderMapper.class);
}
