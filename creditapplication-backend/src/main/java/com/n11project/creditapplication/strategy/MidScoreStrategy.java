package com.n11project.creditapplication.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MidScoreStrategy implements CalculateLimitStrategy{

    @Override
    public BigDecimal calculateLimit(BigDecimal monthlyIncome, BigDecimal assurance) {
        return new BigDecimal(20000).add(assurance.divide(new BigDecimal(5)));
    }

    @Override
    public Boolean isSuitable(BigDecimal monthlyIncome, Integer creditScore) {
        return creditScore < 1000 && monthlyIncome.compareTo(new BigDecimal(10000)) < 0;
    }
}
