package com.n11project.creditapplication.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.n11project.creditapplication.constants.CreditMultiplierConstant.CREDIT_LIMIT_MULTIPLIER;

@Component
public class HighScoreStrategy implements CalculateLimitStrategy{

    @Override
    public Double calculateLimit(Double monthlyIncome, Double assurance) {
        return monthlyIncome * CREDIT_LIMIT_MULTIPLIER + assurance / 2 ;
    }

    @Override
    public Boolean isSuitable(Double monthlyIncome , Integer creditScore) {
        return creditScore >= 1000;
    }
}
