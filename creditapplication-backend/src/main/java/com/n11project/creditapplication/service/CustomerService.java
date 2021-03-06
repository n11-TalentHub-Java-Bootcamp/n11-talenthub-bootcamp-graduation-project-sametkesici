package com.n11project.creditapplication.service;

import static java.lang.Boolean.FALSE;

import com.n11project.creditapplication.dto.request.UpdateCustomerRequest;
import com.n11project.creditapplication.exception.CustomerAlreadyExistException;
import com.n11project.creditapplication.exception.CustomerNotFoundException;
import com.n11project.creditapplication.exception.InputMismatchException;
import com.n11project.creditapplication.model.Customer;
import com.n11project.creditapplication.repository.CustomerRepository;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  private final CreditScoreService creditScoreService;

  @Transactional
  public Customer createCustomer(Customer customer) {
    String identificationNumber = customer.getIdentificationNumber();
    String phoneNumber = customer.getPhoneNumber();
    Double assurance = checkAssuranceIsNullAndReturnAssurance(customer.getAssurance());

    if (customerRepository.findByIdentificationNumber(identificationNumber).isEmpty() && FALSE.equals(
        checkPhoneNumberIsAlreadyUsed(phoneNumber))) {
      Integer creditScore = creditScoreService.getCreditScore();
      customer.setAssurance(assurance);
      customer.setCreditScore(creditScore);
      return saveCustomer(customer);
    }
    log.error("Customer already exist");
    throw new CustomerAlreadyExistException();
  }

  @Transactional
  public Customer updateCustomer(String identificationNumber, UpdateCustomerRequest updateCustomerRequest) {
    Customer customer = findCustomerByIdentificationNumberOrThrowException(identificationNumber);
    Double monthlyIncome = updateCustomerRequest.getMonthlyIncome();
    Double assurance = checkAssuranceIsNullAndReturnAssurance(updateCustomerRequest.getAssurance());
    customer.setMonthlyIncome(monthlyIncome);
    customer.setAssurance(assurance);
    log.info("Customer update -> {}" , customer.getId());
    return saveCustomer(customer);
  }

  @Transactional
  public void deleteByIdentificationNumber(String identificationNumber) {
    Customer customer = findCustomerByIdentificationNumberOrThrowException(identificationNumber);
    log.debug("Customer id want to delete -> {}", customer.getId());
    customerRepository.deleteById(customer.getId());
  }

  public Customer findCustomerByIdentificationNumberAndBirthDateOrThrowException(String identificationNumber,
                                                                                 Date birthDate) {
    return customerRepository
        .findByIdentificationNumberAndBirthDate(identificationNumber, birthDate)
        .orElseThrow(InputMismatchException::new);
  }

  public Customer saveCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  private Double checkAssuranceIsNullAndReturnAssurance(Double assurance) {
    if (Objects.isNull(assurance)) {
      return 0.0;
    }
    return assurance;
  }

  private Boolean checkPhoneNumberIsAlreadyUsed(String phoneNumber) {
    return findAllPhoneNumbers().contains(phoneNumber);
  }

  private Set<String> findAllPhoneNumbers() {
    List<Customer> customerList = customerRepository.findAll();
    return customerList.stream().map(Customer::getPhoneNumber).collect(Collectors.toSet());
  }

  private Customer findCustomerByIdentificationNumberOrThrowException(String identificationNumber) {
    return customerRepository
        .findByIdentificationNumber(identificationNumber)
        .orElseThrow(CustomerNotFoundException::new);
  }
}
