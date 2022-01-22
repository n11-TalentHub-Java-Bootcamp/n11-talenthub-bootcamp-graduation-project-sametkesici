package com.n11project.creditapplication.strategy;

import java.math.RoundingMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LowScoreStrategy implements CalculateLimitStrategy{

    @Override
    public BigDecimal calculateLimit(BigDecimal monthlyIncome , BigDecimal assurance) {
        return new BigDecimal(10000).add(assurance.divide(new BigDecimal(10),RoundingMode.CEILING));
    }

    @Override
    public Boolean isSuitable(BigDecimal monthlyIncome , Integer creditScore) {
        return creditScore < 1000 && monthlyIncome.compareTo(new BigDecimal(5000)) < 0;
    }
}
