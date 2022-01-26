package com.n11project.creditapplication.service;

import com.n11project.creditapplication.exception.ApplicationNotFoundException;
import com.n11project.creditapplication.model.Application;
import com.n11project.creditapplication.model.Customer;
import com.n11project.creditapplication.repository.ApplicationRepository;
import com.n11project.creditapplication.strategy.StrategyContext;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final StrategyContext strategyContext;

    private final CustomerService customerService;


    @Transactional
    public Application makeApplication (Customer customer){
        Double monthlyIncome = customer.getMonthlyIncome();
        Double assurance = customer.getAssurance();
        Integer creditScore = customer.getCreditScore();
        Application application = strategyContext.calculateLimitAndSetStatus(monthlyIncome,creditScore,assurance);
        application.setCustomer(customer);
        return applicationRepository.save(application);
    }

    @Transactional
    public Application updateApplication (Customer customer){
        Application application = findApplicationByCustomerOrThrowException(customer);
        Integer creditScore = customer.getCreditScore();
        Double monthlyIncome = customer.getMonthlyIncome();
        Double assurance = customer.getAssurance();
        Double creditLimit = strategyContext.calculateLimitAndSetStatus(monthlyIncome,creditScore,assurance).getCreditLimit();
        application.setCreditLimit(creditLimit);
        return applicationRepository.save(application);
    }

    public Application findApplicationByIdentificationNumberAndBirthDateOrThrowException(String identificationNumber , Date birthDate) {
        Customer customer = customerService.findCustomerByIdentificationNumberAndBirthDateOrThrowException(identificationNumber,birthDate);
        return findApplicationByCustomerOrThrowException(customer);
    }

    private Application findApplicationByCustomerOrThrowException(Customer customer){
        return applicationRepository.findByCustomer(customer).orElseThrow(ApplicationNotFoundException::new);
    }
}
