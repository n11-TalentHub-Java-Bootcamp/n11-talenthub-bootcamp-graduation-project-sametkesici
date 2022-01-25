package com.n11project.creditapplication.service;

import static com.n11project.creditapplication.model.ApplicationStatus.APPROVED;
import static org.junit.jupiter.api.Assertions.*;

import com.n11project.creditapplication.model.Application;
import com.n11project.creditapplication.model.Customer;
import com.n11project.creditapplication.repository.ApplicationRepository;
import com.n11project.creditapplication.strategy.StrategyContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import liquibase.pro.packaged.B;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
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
  void makeApplication() {
    Customer customer = Customer.builder().id(1L).name("Samet").lastName("Kesici").identificationNumber("50461491404").phoneNumber("5347208277")
                                .assurance(new BigDecimal(0)).creditScore(100).birthDate(new Date()).monthlyIncome(new BigDecimal(5000)).build();

    Application application = Application.builder().applicationStatus(APPROVED).creditLimit(new BigDecimal(500)).customer(customer).build();
    when(strategyContext.calculateLimitAndSetStatus(customer.getMonthlyIncome(),customer.getCreditScore(),customer.getAssurance())).thenReturn(application);
    applicationService.makeApplication(customer);
    ArgumentCaptor<Application> applicationArgumentCaptor = ArgumentCaptor.forClass(Application.class);
    verify(applicationRepository).save(applicationArgumentCaptor.capture());
    Application savedApplication = applicationArgumentCaptor.getValue();

    assertEquals(APPROVED,savedApplication.getApplicationStatus());
    assertEquals(new BigDecimal(500),savedApplication.getCreditLimit());
    assertEquals(1L,savedApplication.getCustomer().getId());

  }

  @Test
  void updateApplication() {

    Customer customer = Customer.builder().id(1L).name("Samet").lastName("Kesici").identificationNumber("50461491404").phoneNumber("5347208277")
                                .assurance(new BigDecimal(0)).creditScore(100).birthDate(new Date()).monthlyIncome(new BigDecimal(5000)).build();

    Application application = Application.builder().applicationStatus(APPROVED).creditLimit(new BigDecimal(500)).customer(customer).build();

    when(applicationRepository.findByCustomer(customer)).thenReturn(Optional.of(application));
    when(strategyContext.calculateLimitAndSetStatus(customer.getMonthlyIncome(),customer.getCreditScore(),customer.getAssurance())).thenReturn(application);
    applicationService.updateApplication(customer);
    ArgumentCaptor<Application> applicationArgumentCaptor = ArgumentCaptor.forClass(Application.class);
    verify(applicationRepository).save(applicationArgumentCaptor.capture());

  }

  @Test
  void findApplicationByIdentificationNumberAndBirthDateOrThrowException() {
    Customer customer = Customer.builder().id(1L).name("Samet").lastName("Kesici").identificationNumber("50461491404").phoneNumber("5347208277")
                                .assurance(new BigDecimal(0)).creditScore(100).birthDate(new Date()).monthlyIncome(new BigDecimal(5000)).build();

    Application application = Application.builder().applicationStatus(APPROVED).creditLimit(new BigDecimal(500)).customer(customer).build();
    Application application2 = new Application();

    when(applicationService.findApplicationByIdentificationNumberAndBirthDateOrThrowException("50461491404",customer.getBirthDate())).thenReturn(application);
    when(customerService.findCustomerByIdentificationNumberAndBirthDateOrThrowException("50461491404",customer.getBirthDate())).thenReturn(customer);

    when(applicationRepository.findByCustomer(customer)).thenReturn(Optional.of(application2));

  }
}