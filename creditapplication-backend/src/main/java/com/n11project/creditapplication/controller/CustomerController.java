package com.n11project.creditapplication.controller;

import com.n11project.creditapplication.dto.CustomerDto;
import com.n11project.creditapplication.dto.request.customer.CreateCustomerRequest;
import com.n11project.creditapplication.mapper.CreateCustomerMapper;
import com.n11project.creditapplication.mapper.CustomerMapper;
import com.n11project.creditapplication.model.Customer;
import com.n11project.creditapplication.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final CreateCustomerMapper createCustomerMapper;

    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        Customer customer = customerService.create(createCustomerMapper.toEntity(createCustomerRequest));
        CustomerDto customerDto = customerMapper.toDto(customer);
        return ResponseEntity.ok(customerDto);
    }

}
