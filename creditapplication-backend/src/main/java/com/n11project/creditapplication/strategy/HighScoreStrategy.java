package com.n11project.creditapplication.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.n11project.creditapplication.constants.CreditMultiplierConstant.CREDIT_LIMIT_MULTIPLIER;

@Component
public class HighScoreStrategy implements CalculateLimitStrategy{

    @Override
    public BigDecimal calculateLimit(BigDecimal monthlyIncome, BigDecimal assurance) {
        return monthlyIncome.multiply(CREDIT_LIMIT_MULTIPLIER).add(assurance.divide(new BigDecimal(2), RoundingMode.CEILING));
    }

    @Override
    public Boolean isSuitable(BigDecimal monthlyIncome , Integer creditScore) {
        return creditScore >= 1000;
    }
}
