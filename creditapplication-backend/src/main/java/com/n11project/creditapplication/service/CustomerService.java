package com.n11project.creditapplication.service;

import com.n11project.creditapplication.dto.request.customer.CreateCustomerRequest;
import com.n11project.creditapplication.model.Customer;
import com.n11project.creditapplication.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }
}
