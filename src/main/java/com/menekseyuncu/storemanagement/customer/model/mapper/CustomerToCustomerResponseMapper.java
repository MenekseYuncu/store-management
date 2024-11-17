package com.menekseyuncu.storemanagement.customer.model.mapper;

import com.menekseyuncu.storemanagement.common.model.BaseMapper;
import com.menekseyuncu.storemanagement.customer.controller.response.CustomerResponse;
import com.menekseyuncu.storemanagement.customer.model.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerToCustomerResponseMapper extends BaseMapper<Customer, CustomerResponse> {

    CustomerToCustomerResponseMapper INSTANCE = Mappers.getMapper(CustomerToCustomerResponseMapper.class);
}
