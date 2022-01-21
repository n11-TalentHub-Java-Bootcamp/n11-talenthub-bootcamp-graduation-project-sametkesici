package com.n11project.creditapplication.mapper.customer;

import com.n11project.creditapplication.dto.CustomerDto;
import com.n11project.creditapplication.mapper.BaseMapper;
import com.n11project.creditapplication.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends BaseMapper<Customer, CustomerDto> {

}
