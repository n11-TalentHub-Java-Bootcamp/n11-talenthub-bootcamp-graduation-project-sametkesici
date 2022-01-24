package com.n11project.creditapplication.strategy;

import java.math.RoundingMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.n11project.creditapplication.constants.CreditMultiplierConstant.CREDIT_LIMIT_MULTIPLIER;

@Component
public class MidScoreAndHighIncomeStrategy implements CalculateLimitStrategy{

    @Override
    public BigDecimal calculateLimit(BigDecimal monthlyIncome, BigDecimal assurance) {
        return monthlyIncome.multiply(CREDIT_LIMIT_MULTIPLIER.divide(new BigDecimal(2),RoundingMode.CEILING)).add(assurance.divide(new BigDecimal(4),RoundingMode.CEILING));
    }

    @Override
    public Boolean isSuitable(BigDecimal monthlyIncome, Integer creditScore) {
        return creditScore < 1000 && creditScore > 500 && monthlyIncome.compareTo(new BigDecimal(10000)) >= 0;
    }


}
