package com.n11project.creditapplication.strategy;

import java.math.RoundingMode;
import liquibase.pro.packaged.B;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MidScoreStrategy implements CalculateLimitStrategy{

    @Override
    public Double calculateLimit(Double monthlyIncome, Double assurance) {
        return 20000 + assurance / 5 ;
    }

    @Override
    public Boolean isSuitable(Double monthlyIncome, Integer creditScore) {
        return creditScore < 1000 && creditScore > 500 &&  monthlyIncome > 5000 && monthlyIncome < 10000;
    }
}
