package com.n11project.creditapplication.service.strategy;

import static com.n11project.creditapplication.constant.CreditMultiplierConstant.CREDIT_LIMIT_MULTIPLIER;

import org.springframework.stereotype.Component;

@Component
public class HighScoreStrategy implements CalculateLimitStrategy {

  @Override
  public Double calculateLimit(Double monthlyIncome, Double assurance) {
    return monthlyIncome * CREDIT_LIMIT_MULTIPLIER + assurance / 2;
  }

  @Override
  public Boolean isSuitable(Double monthlyIncome, Integer creditScore) {
    return creditScore >= 1000;
  }
}