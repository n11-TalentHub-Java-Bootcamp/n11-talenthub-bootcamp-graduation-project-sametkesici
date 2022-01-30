package com.n11project.creditapplication.service;

import static com.n11project.creditapplication.model.ApplicationStatus.APPROVED;

import com.n11project.creditapplication.model.Application;
import com.n11project.creditapplication.model.Customer;
import com.n11project.creditapplication.repository.ApplicationRepository;
import com.n11project.creditapplication.service.strategy.StrategyContext;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.n11project.creditapplication.model.ApplicationStatus.REJECTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

  @Mock
  private  ApplicationRepository applicationRepository;

  @Mock
  private StrategyContext strategyContext;

  @Mock
  private CustomerService customerService;

  @InjectMocks
  private ApplicationService applicationService;


  @Test
  void shouldMakeApplicationWhen() {
    Customer customer = Customer.builder().id(1L).name("Samet").lastName("Kesici").identificationNumber("50461491404").phoneNumber("5347208277")
                                .assurance(0.0).creditScore(100).birthDate(new Date()).monthlyIncome(5000.0).build();
    Application application = Application.builder().applicationStatus(APPROVED).creditLimit(600.0).customer(customer).build();

    when(strategyContext.calculateLimitAndSetStatus(customer.getMonthlyIncome(),customer.getCreditScore(),customer.getAssurance())).thenReturn(application);
    applicationService.makeApplication(customer);
    ArgumentCaptor<Application> applicationArgumentCaptor = ArgumentCaptor.forClass(Application.class);
    verify(applicationRepository).save(applicationArgumentCaptor.capture());
    Application savedApplication = applicationArgumentCaptor.getValue();
    assertEquals(APPROVED,savedApplication.getApplicationStatus());
    assertEquals(600.0,savedApplication.getCreditLimit());
    assertEquals(1L,savedApplication.getCustomer().getId());
  }

  @Test
  void updateApplication() {

    Customer customer = Customer.builder().id(1L).name("Samet").lastName("Kesici").identificationNumber("50461491404").phoneNumber("5347208277")
                                .assurance(0.0).creditScore(100).birthDate(new Date()).monthlyIncome(5000.0).build();

    Application application = Application.builder().applicationStatus(REJECTED).creditLimit(300.0).customer(customer).build();

    when(applicationRepository.findByCustomer(customer)).thenReturn(Optional.of(application));
    when(strategyContext.calculateLimitAndSetStatus(customer.getMonthlyIncome(),customer.getCreditScore(),customer.getAssurance())).thenReturn(application);
    applicationService.updateApplication(customer);
    ArgumentCaptor<Application> applicationArgumentCaptor = ArgumentCaptor.forClass(Application.class);
    verify(applicationRepository).save(applicationArgumentCaptor.capture());

    Application updatedApplication = applicationArgumentCaptor.getValue();
    assertEquals(REJECTED,updatedApplication.getApplicationStatus());
    assertEquals(300,updatedApplication.getCreditLimit());
    assertEquals(customer,application.getCustomer());

  }

  @Test
  void shouldFindApplicationWhenFoundCustomerByIdentificationNumberAndBirthDate() {
    Customer customer = Customer.builder().id(1L).name("Samet").lastName("Kesici").identificationNumber("121212345").phoneNumber("5347208277")
                                .assurance(0.0).creditScore(100).birthDate(new Date()).monthlyIncome(1000.0).build();

    Application application = Application.builder().applicationStatus(APPROVED).creditLimit(600.0).customer(customer).build();

    when(customerService.findCustomerByIdentificationNumberAndBirthDateOrThrowException(customer.getIdentificationNumber(),customer.getBirthDate())).thenReturn(customer);
    when(applicationRepository.findByCustomer(customer)).thenReturn(Optional.of(application));

    Application expectedApplication = applicationService.findApplicationByIdentificationNumberAndBirthDateOrThrowException("121212345",customer.getBirthDate());

    assertEquals(APPROVED,expectedApplication.getApplicationStatus());

  }
}