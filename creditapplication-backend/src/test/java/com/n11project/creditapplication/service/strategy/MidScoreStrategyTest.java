package com.n11project.creditapplication.service.strategy;

import static com.n11project.creditapplication.constants.CreditMultiplierConstant.CREDIT_LIMIT_MULTIPLIER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MidScoreStrategyTest {

  MidScoreStrategy midScoreStrategy;

  @BeforeEach
  void setUp() {
    midScoreStrategy = new MidScoreStrategy();
  }

  @Test
  void shouldCalculateLimitCorrectly() {

    Double monthlyIncome = 6000.0;
    Double assurance = 100.0;
    Double creditLimit = 20000 + assurance / 5;
    Double expectedCreditLimit = midScoreStrategy.calculateLimit(monthlyIncome, assurance);

    assertEquals(creditLimit, expectedCreditLimit);
  }

  @Test
  void shouldSuitForCalculate() {
    Double monthlyIncome = 7000.0;
    Integer creditScore = 600;

    Boolean expected = midScoreStrategy.isSuitable(monthlyIncome, creditScore);

    assertEquals(TRUE, expected);
  }

  @Test
  void shouldNotSuitForCalculate() {

    Double monthlyIncome = 10500.0;
    Integer creditScore = 400;
    Boolean expected = midScoreStrategy.isSuitable(monthlyIncome, creditScore);
    assertEquals(FALSE, expected);
  }
}