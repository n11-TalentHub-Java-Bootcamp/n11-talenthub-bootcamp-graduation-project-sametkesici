package com.n11project.creditapplication.service;

import com.n11project.creditapplication.dto.request.customer.UpdateCustomerRequest;
import com.n11project.creditapplication.exception.CustomerNotFoundException;
import com.n11project.creditapplication.model.Application;
import com.n11project.creditapplication.model.Customer;
import com.n11project.creditapplication.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CreditScoreService creditScoreService;


    @Transactional
    public Customer createCustomer(Customer customer) {
        Integer creditScore = creditScoreService.getCreditScore();
        customer.setCreditScore(creditScore);
        return saveCustomer(customer);
    }

    @Transactional
    public void deleteById(String identificationNumber) {
        Customer customer = findCustomerByIdentificationNumberOrThrowException(identificationNumber);
        customerRepository.deleteById(customer.getId());
    }

    @Transactional
    public Customer updateCustomer(String identificationNumber, UpdateCustomerRequest updateCustomerRequest) {

            Customer customer = findCustomerByIdentificationNumberOrThrowException(identificationNumber);

            BigDecimal monthlyIncome = updateCustomerRequest.getMonthlyIncome();
            String phoneNumber = updateCustomerRequest.getPhoneNumber();
            BigDecimal assurance = updateCustomerRequest.getAssurance();

            if (Objects.nonNull(monthlyIncome)) {
                customer.setMonthlyIncome(monthlyIncome);
            }
            if (Objects.nonNull(phoneNumber)) {
                customer.setPhoneNumber(phoneNumber);
            }
            if (Objects.nonNull(assurance)) {
                customer.setAssurance(assurance);
            }
            return saveCustomer(customer);
    }

    @Transactional
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }


    public Customer findCustomerByIdentificationNumberOrThrowException(String identificationNumber){
        return customerRepository.findByIdentificationNumber(identificationNumber).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer findCustomerByIdentificationNumberAndBirthDateOrThrowException(String identificationNumber , Date birthDate){
        return customerRepository.findByIdentificationNumberAndBirthDate(identificationNumber,birthDate).orElseThrow(CustomerNotFoundException::new);
    }


}
