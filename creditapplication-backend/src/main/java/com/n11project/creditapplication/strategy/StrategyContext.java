package com.n11project.creditapplication.strategy;

import com.n11project.creditapplication.exception.StrategyNotFoundException;
import com.n11project.creditapplication.model.Application;
import com.n11project.creditapplication.model.ApplicationStatus;
import liquibase.pro.packaged.A;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;


@Component
@RequiredArgsConstructor
public class StrategyContext {

    private final List<CalculateLimitStrategy> calculateLimitStrategyList;

    public BigDecimal getCorrectStrategyAndCalculateLimit(BigDecimal monthlyIncome , Integer creditScore, BigDecimal assurance){
        CalculateLimitStrategy calculateLimitStrategy = calculateLimitStrategyList.stream().filter(x -> x.isSuitable(monthlyIncome,creditScore)).findFirst().orElseThrow(StrategyNotFoundException::new);
        return calculateLimitStrategy.calculateLimit(monthlyIncome,assurance);
    }

    public Application calculateLimitAndSetStatus(BigDecimal monthlyIncome , Integer creditScore , BigDecimal assurance){
        Application application = new Application();
        setApplicationStatus(creditScore, application);
        BigDecimal creditLimit = getCorrectStrategyAndCalculateLimit(monthlyIncome,creditScore,assurance);
        application.setCreditLimit(creditLimit);
        return application;
    }

    private void setApplicationStatus(Integer creditScore, Application application) {
        if(creditScore < 500){
            application.setApplicationStatus(ApplicationStatus.REJECT);
        }else{
            application.setApplicationStatus(ApplicationStatus.APPROVE);
        }
    }
}

