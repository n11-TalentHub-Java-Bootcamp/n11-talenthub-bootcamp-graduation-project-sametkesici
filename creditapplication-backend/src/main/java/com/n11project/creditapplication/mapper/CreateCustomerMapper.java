package com.n11project.creditapplication.mapper;

import com.n11project.creditapplication.dto.request.customer.CreateCustomerRequest;
import com.n11project.creditapplication.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateCustomerMapper extends BaseMapper<Customer, CreateCustomerRequest> {
}
