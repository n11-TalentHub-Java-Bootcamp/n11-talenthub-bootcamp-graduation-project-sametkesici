package com.n11project.creditapplication.controller;

import com.n11project.creditapplication.dto.ApplicationDto;
import com.n11project.creditapplication.dto.CustomerDto;
import com.n11project.creditapplication.dto.request.customer.CreateCustomerRequest;
import com.n11project.creditapplication.dto.request.customer.UpdateCustomerRequest;
import com.n11project.creditapplication.mapper.customer.CreateCustomerMapper;
import com.n11project.creditapplication.mapper.customer.CustomerMapper;
import com.n11project.creditapplication.model.Application;
import com.n11project.creditapplication.model.Customer;
import com.n11project.creditapplication.service.ApplicationService;
import com.n11project.creditapplication.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    private final CreateCustomerMapper createCustomerMapper;

    private final ApplicationService applicationService;

    private static final Logger logger = LogManager.getLogger(CustomerController.class);

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        logger.info("berkerik");
        Customer customer = customerService.createCustomer(createCustomerMapper.toEntity(createCustomerRequest));
        applicationService.makeApplication(customer);
        CustomerDto customerDto = customerMapper.toDto(customer);
        return ResponseEntity.ok(customerDto);
    }

    @DeleteMapping("/{identificationNumber}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String identificationNumber){
        customerService.deleteById(identificationNumber);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/{identificationNumber}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String identificationNumber , UpdateCustomerRequest updateCustomerRequest){
        Customer customer = customerService.updateCustomer(identificationNumber,updateCustomerRequest);
        applicationService.makeApplication(customer);
        CustomerDto customerDto = customerMapper.toDto(customer);
        return ResponseEntity.ok(customerDto);
    }

}
