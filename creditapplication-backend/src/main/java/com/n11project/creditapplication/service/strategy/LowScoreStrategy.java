package com.n11project.creditapplication.service.strategy;

import org.springframework.stereotype.Component;

@Component
public class LowScoreStrategy implements CalculateLimitStrategy {

  @Override
  public Double calculateLimit(Double monthlyIncome, Double assurance) {
    return 10000 + assurance / 10;
  }

  @Override
  public Boolean isSuitable(Double monthlyIncome, Integer creditScore) {
    return creditScore < 1000 && creditScore > 500 && monthlyIncome < 5000;
  }
}