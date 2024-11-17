package com.menekseyuncu.storemanagement.customer.model.mapper;

import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import com.menekseyuncu.storemanagement.customer.model.domain.Customer;
import com.menekseyuncu.storemanagement.customer.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerEntityToDomainMapper extends BaseMapper<CustomerEntity, Customer> {
    CustomerEntityToDomainMapper INSTANCE = Mappers.getMapper(CustomerEntityToDomainMapper.class);
}

