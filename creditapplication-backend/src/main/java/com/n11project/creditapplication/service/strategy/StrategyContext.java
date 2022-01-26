package com.n11project.creditapplication.service.strategy;

import static com.n11project.creditapplication.model.ApplicationStatus.APPROVED;
import static com.n11project.creditapplication.model.ApplicationStatus.REJECTED;

import com.n11project.creditapplication.exception.StrategyNotFoundException;
import com.n11project.creditapplication.model.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@RequiredArgsConstructor
public class StrategyContext {

    private final List<CalculateLimitStrategy> calculateLimitStrategyList;

    public Double getCorrectStrategy(Double monthlyIncome , Integer creditScore, Double assurance){
        CalculateLimitStrategy calculateLimitStrategy = calculateLimitStrategyList.stream().filter(strategy -> strategy.isSuitable(monthlyIncome,creditScore)).findFirst().orElseThrow(StrategyNotFoundException::new);
        return calculateLimitStrategy.calculateLimit(monthlyIncome,assurance);
    }

    public Application calculateLimitAndSetStatus(Double monthlyIncome , Integer creditScore , Double assurance){
        Application application = new Application();
        setApplicationStatus(creditScore, application);
        Double creditLimit = getCorrectStrategy(monthlyIncome,creditScore,assurance);
        application.setCreditLimit(creditLimit);
        return application;
    }

    private void setApplicationStatus(Integer creditScore, Application application) {
        if(creditScore < 500){
            application.setApplicationStatus(REJECTED);
        }else{
            application.setApplicationStatus(APPROVED);
        }
    }
}

