package com.n11project.creditapplication.controller;

import com.n11project.creditapplication.dto.request.CreateCustomerRequest;
import com.n11project.creditapplication.dto.request.UpdateCustomerRequest;
import com.n11project.creditapplication.dto.response.ApplicationResponse;
import com.n11project.creditapplication.mapper.ApplicationResponseMapper;
import com.n11project.creditapplication.mapper.CreateCustomerMapper;
import com.n11project.creditapplication.model.Application;
import com.n11project.creditapplication.model.Customer;
import com.n11project.creditapplication.service.ApplicationService;
import com.n11project.creditapplication.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final CreateCustomerMapper createCustomerMapper;

    private final ApplicationService applicationService;

    private final ApplicationResponseMapper applicationResponseMapper;


    @PostMapping
    public ResponseEntity<ApplicationResponse> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        Customer customer = customerService.createCustomer(createCustomerMapper.toEntity(createCustomerRequest));
        Application application = applicationService.makeApplication(customer);
        ApplicationResponse applicationResponse = applicationResponseMapper.toDto(application);
        return ResponseEntity.ok(applicationResponse);
    }

    @PutMapping("/{identificationNumber}")
    public ResponseEntity<ApplicationResponse> updateCustomer(@PathVariable String identificationNumber , UpdateCustomerRequest updateCustomerRequest){
        Customer customer = customerService.updateCustomer(identificationNumber,updateCustomerRequest);
        Application application = applicationService.updateApplication(customer);
        ApplicationResponse applicationResponse = applicationResponseMapper.toDto(application);
        return ResponseEntity.ok(applicationResponse);
    }

    @DeleteMapping("/{identificationNumber}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String identificationNumber){
        customerService.deleteByIdentificationNumber(identificationNumber);
        return ResponseEntity.ok("OK");
    }

}
