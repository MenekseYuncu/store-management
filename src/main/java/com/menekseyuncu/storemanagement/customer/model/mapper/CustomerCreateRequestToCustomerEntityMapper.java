package com.menekseyuncu.storemanagement.customer.model.mapper;


import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import com.menekseyuncu.storemanagement.customer.controller.request.CustomerCreateRequest;
import com.menekseyuncu.storemanagement.customer.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerCreateRequestToCustomerEntityMapper extends BaseMapper<CustomerCreateRequest, CustomerEntity> {

    CustomerCreateRequestToCustomerEntityMapper INSTANCE = Mappers.getMapper(CustomerCreateRequestToCustomerEntityMapper.class);

}
