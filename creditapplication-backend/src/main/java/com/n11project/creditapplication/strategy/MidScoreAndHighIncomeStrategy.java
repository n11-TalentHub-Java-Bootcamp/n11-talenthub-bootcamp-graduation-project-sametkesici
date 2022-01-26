package com.n11project.creditapplication.strategy;

import java.math.RoundingMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.n11project.creditapplication.constants.CreditMultiplierConstant.CREDIT_LIMIT_MULTIPLIER;

@Component
public class MidScoreAndHighIncomeStrategy implements CalculateLimitStrategy{

    @Override
    public Double calculateLimit(Double monthlyIncome, Double assurance) {
        return monthlyIncome * CREDIT_LIMIT_MULTIPLIER / 2 + assurance / 4 ;
    }

    @Override
    public Boolean isSuitable(Double monthlyIncome, Integer creditScore) {
        return creditScore < 1000 && creditScore > 500 && monthlyIncome >= 10000;
    }


}
