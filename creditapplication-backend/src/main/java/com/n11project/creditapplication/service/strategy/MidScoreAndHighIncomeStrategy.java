package com.n11project.creditapplication.service.strategy;

import static com.n11project.creditapplication.constant.CreditMultiplierConstant.CREDIT_LIMIT_MULTIPLIER;

import org.springframework.stereotype.Component;

@Component
public class MidScoreAndHighIncomeStrategy implements CalculateLimitStrategy {

  @Override
  public Double calculateLimit(Double monthlyIncome, Double assurance) {
    return monthlyIncome * CREDIT_LIMIT_MULTIPLIER / 2 + assurance / 4;
  }

  @Override
  public Boolean isSuitable(Double monthlyIncome, Integer creditScore) {
    return creditScore < 1000 && creditScore > 500 && monthlyIncome >= 10000;
  }
}