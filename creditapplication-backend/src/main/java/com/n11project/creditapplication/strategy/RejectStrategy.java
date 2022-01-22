package com.n11project.creditapplication.strategy;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class RejectStrategy implements CalculateLimitStrategy{

    @Override
    public BigDecimal calculateLimit(BigDecimal monthlyIncome, BigDecimal assurance) {
        return new BigDecimal(0);
    }

    @Override
    public Boolean isSuitable(BigDecimal monthlyIncome, Integer creditScore) {
        return creditScore < 500 ;
    }
}
