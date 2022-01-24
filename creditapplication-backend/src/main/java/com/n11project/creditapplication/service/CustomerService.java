package com.n11project.creditapplication.service;

import static java.lang.Boolean.FALSE;

import com.n11project.creditapplication.dto.request.UpdateCustomerRequest;
import com.n11project.creditapplication.exception.CustomerAlreadyExistException;
import com.n11project.creditapplication.exception.CustomerNotFoundException;
import com.n11project.creditapplication.exception.InputMismatchException;
import com.n11project.creditapplication.exception.PhoneNumberAlreadyExistException;
import com.n11project.creditapplication.model.Customer;
import com.n11project.creditapplication.repository.CustomerRepository;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import liquibase.pro.packaged.B;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CreditScoreService creditScoreService;


    @Transactional
    public Customer createCustomer(Customer customer) {
        String identificationNumber = customer.getIdentificationNumber();
        String phoneNumber = customer.getPhoneNumber();
        BigDecimal assurance = checkAssuranceIsNullAndReturnAssurance(customer.getAssurance());

        if(customerRepository.findByIdentificationNumber(identificationNumber).isEmpty() && FALSE.equals(checkPhoneNumberIsSave(phoneNumber))){
            Integer creditScore = creditScoreService.getCreditScore();
            customer.setAssurance(assurance);
            customer.setCreditScore(creditScore);
            return saveCustomer(customer);
        }
        throw new CustomerAlreadyExistException();
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
        BigDecimal assurance = checkAssuranceIsNullAndReturnAssurance(updateCustomerRequest.getAssurance());

        if(FALSE.equals(checkPhoneNumberIsSave(phoneNumber))){
          customer.setMonthlyIncome(monthlyIncome);
          customer.setPhoneNumber(phoneNumber);
          customer.setAssurance(assurance);
          return saveCustomer(customer);
        }
        throw new PhoneNumberAlreadyExistException();
    }

    public BigDecimal checkAssuranceIsNullAndReturnAssurance(BigDecimal assurance) {
        if(Objects.isNull(assurance)){
            return new BigDecimal(0);
        }
        return assurance;
    }

    public Boolean checkPhoneNumberIsSave(String phoneNumber){
        return findAllPhoneNumbers().contains(phoneNumber);
    }

    public Set<String> findAllPhoneNumbers(){
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().map(Customer::getPhoneNumber).collect(Collectors.toSet());
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }


    public Customer findCustomerByIdentificationNumberOrThrowException(String identificationNumber){
        return customerRepository.findByIdentificationNumber(identificationNumber).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer findCustomerByIdentificationNumberAndBirthDateOrThrowException(String identificationNumber , Date birthDate){
        return customerRepository.findByIdentificationNumberAndBirthDate(identificationNumber,birthDate).orElseThrow(InputMismatchException::new);
    }


}
