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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

  private final CustomerService customerService;

  private final CreateCustomerMapper createCustomerMapper;

  private final ApplicationService applicationService;

  private final ApplicationResponseMapper applicationResponseMapper;

  @PostMapping
  public ResponseEntity<ApplicationResponse> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
    Customer customer = createCustomerMapper.toEntity(createCustomerRequest);
    customerService.createCustomer(customer);
    Application application = applicationService.makeApplication(customer);
    ApplicationResponse applicationResponse = applicationResponseMapper.toDto(application);
    log.info("Create customer request -> {}", createCustomerRequest.getIdentificationNumber());
    return ResponseEntity.ok(applicationResponse);
  }

  @PutMapping("/{identificationNumber}")
  public ResponseEntity<ApplicationResponse> updateCustomer(@PathVariable String identificationNumber,
                                                            UpdateCustomerRequest updateCustomerRequest) {
    Customer customer = customerService.updateCustomer(identificationNumber, updateCustomerRequest);
    Application application = applicationService.updateApplication(customer);
    ApplicationResponse applicationResponse = applicationResponseMapper.toDto(application);
    log.info("Update customer request -> {}", identificationNumber);
    return ResponseEntity.ok(applicationResponse);
  }

  @DeleteMapping("/{identificationNumber}")
  public ResponseEntity<String> deleteCustomer(@PathVariable String identificationNumber) {
    customerService.deleteByIdentificationNumber(identificationNumber);
    log.debug("Delete customer request -> {}", identificationNumber);
    return ResponseEntity.ok("OK");
  }
}
