package com.n11project.creditapplication.service;


import com.n11project.creditapplication.dto.request.UpdateCustomerRequest;
import com.n11project.creditapplication.exception.CustomerAlreadyExistException;
import com.n11project.creditapplication.exception.InputMismatchException;
import com.n11project.creditapplication.exception.PhoneNumberAlreadyExistException;
import com.n11project.creditapplication.model.Customer;
import com.n11project.creditapplication.repository.CustomerRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import liquibase.pro.packaged.D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @Mock
  private CreditScoreService creditScoreService;

  @InjectMocks
  private CustomerService customerService;

  @Test
  void shouldCreateUserWhenIdentificationNumberAndPhoneNumberNotUsed(){
      Customer customer = Customer.builder().name("Samet").lastName("Kesici").identificationNumber("50461491404").phoneNumber("5347208277")
                                  .assurance(new BigDecimal(0)).birthDate(new Date()).monthlyIncome(new BigDecimal(5000)).build();
      when(customerRepository.findByIdentificationNumber(customer.getIdentificationNumber())).thenReturn(Optional.empty());
      customerService.createCustomer(customer);
      ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
      verify(customerRepository).save(customerArgumentCaptor.capture());
      Customer createdCustomer = customerArgumentCaptor.getValue();

      assertEquals("Samet" , createdCustomer.getName());
      assertEquals("Kesici" , createdCustomer.getLastName());
      assertEquals("50461491404",createdCustomer.getIdentificationNumber());
      assertEquals("5347208277",createdCustomer.getPhoneNumber());
  }

  @Test
  void shouldCreateUserThrowExceptionWhenIdentificationNumberAndPhoneNumberAreUsed(){
    Customer customer = Customer.builder().name("Samet").lastName("Kesici").identificationNumber("50461491404").phoneNumber("5347208277")
                                .assurance(new BigDecimal(0)).birthDate(new Date()).creditScore(600).monthlyIncome(new BigDecimal(5000)).build();

    when(customerRepository.findByIdentificationNumber(customer.getIdentificationNumber())).thenReturn(Optional.of(new Customer()));

    assertThrows(CustomerAlreadyExistException.class , () -> customerService.createCustomer(customer));
  }

  @Test
  void shouldUpdateCustomerWhenIdentificationNumberIsAlreadyUsed(){
    UpdateCustomerRequest updateCustomerRequest = UpdateCustomerRequest.builder().phoneNumber("5347208277").assurance(new BigDecimal(100)).monthlyIncome(new BigDecimal(10000)).build();
    when(customerRepository.findByIdentificationNumber("50461491404")).thenReturn(Optional.of(Customer.builder().identificationNumber("50461491404").build()));

    customerService.updateCustomer("50461491404",updateCustomerRequest);
    ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

    verify(customerRepository).save(customerArgumentCaptor.capture());
    Customer updatedCustomer = customerArgumentCaptor.getValue();
    assertEquals("5347208277",updatedCustomer.getPhoneNumber());
    assertEquals(new BigDecimal(100), updatedCustomer.getAssurance());
    assertEquals(new BigDecimal(10000),updatedCustomer.getMonthlyIncome());
  }

  @Test
  void shouldUpdateCustomerThrowExceptionWhenPhoneNumberIsAlreadyUsed(){
    UpdateCustomerRequest updateCustomerRequest = UpdateCustomerRequest.builder().phoneNumber("5347208277").assurance(new BigDecimal(100)).monthlyIncome(new BigDecimal(10000)).build();
    Customer customer = Customer.builder().phoneNumber("5347208277").build();

    when(customerRepository.findByIdentificationNumber("50461491404")).thenReturn(Optional.of(customer));

    when(customerRepository.findAll()).thenReturn(List.of(customer));

    assertThrows(PhoneNumberAlreadyExistException.class , () -> customerService.updateCustomer("50461491404",updateCustomerRequest));
  }

  @Test
  void shouldDeleteByIdentificationNumberCallRepositoryExactId(){
      when(customerRepository.findByIdentificationNumber("50461491404")).thenReturn(Optional.of(Customer.builder().id(1L).build()));
      customerService.deleteByIdentificationNumber("50461491404");
      verify(customerRepository).deleteById(1L);
  }

  @Test
  void shouldFindCustomerByIdentificationNumberAndBirthDate(){



  }

  @Test
  void shouldFindCustomerThrowExceptionWhenInputsAreMismatch(){


  }
}