package com.n11project.creditapplication.service.strategy;

import org.springframework.stereotype.Component;

@Component
public class MidScoreStrategy implements CalculateLimitStrategy {

  @Override
  public Double calculateLimit(Double monthlyIncome, Double assurance) {
    return 20000 + assurance / 5;
  }

  @Override
  public Boolean isSuitable(Double monthlyIncome, Integer creditScore) {
    return creditScore < 1000 && creditScore > 500 && monthlyIncome > 5000 && monthlyIncome < 10000;
  }
}