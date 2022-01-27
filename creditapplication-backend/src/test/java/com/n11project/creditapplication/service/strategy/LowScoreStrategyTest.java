package com.n11project.creditapplication.service.strategy;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LowScoreStrategyTest {

  LowScoreStrategy lowScoreStrategy;

  @BeforeEach
  void setUp() {
    lowScoreStrategy = new LowScoreStrategy();
  }

  @Test
  void shouldCalculateLimitCorrectly() {

    Double monthlyIncome = 6000.0;
    Double assurance = 100.0;
    Double creditLimit = 10000 + assurance / 10;
    Double expectedCreditLimit = lowScoreStrategy.calculateLimit(monthlyIncome, assurance);

    assertEquals(creditLimit, expectedCreditLimit);
  }

  @Test
  void shouldSuitForCalculate() {
    Double monthlyIncome = 4500.0;
    Integer creditScore = 600;

    Boolean expected = lowScoreStrategy.isSuitable(monthlyIncome, creditScore);

    assertEquals(TRUE, expected);
  }

  @Test
  void shouldNotSuitForCalculate() {

    Double monthlyIncome = 10500.0;
    Integer creditScore = 400;
    Boolean expected = lowScoreStrategy.isSuitable(monthlyIncome, creditScore);
    assertEquals(FALSE, expected);
  }

}