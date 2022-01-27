package com.n11project.creditapplication.service.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.n11project.creditapplication.constants.CreditMultiplierConstant.CREDIT_LIMIT_MULTIPLIER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;


class HighScoreStrategyTest {

  HighScoreStrategy highScoreStrategy;

  @BeforeEach
  void setUp(){
    highScoreStrategy = new HighScoreStrategy();
  }

  @Test
  void shouldCalculateLimitCorrectly() {

    Double monthlyIncome = 10000.0;
    Double assurance = 5000.0;
    Double creditLimit = monthlyIncome * CREDIT_LIMIT_MULTIPLIER + assurance / 2;
    Double expectedCreditLimit = highScoreStrategy.calculateLimit(monthlyIncome,assurance);

    assertEquals(creditLimit,expectedCreditLimit);

  }

  @Test
  void shouldSuitForCalculate() {

    Double monthlyIncome = 10500.0;
    Integer creditScore  = 1000;

    Boolean expected =  highScoreStrategy.isSuitable(monthlyIncome,creditScore);

    assertEquals(TRUE, expected);

  }

  @Test
  void shouldNotSuitForCalculate() {

    Double monthlyIncome = 10500.0;
    Integer creditScore  = 900;

    Boolean expected =  highScoreStrategy.isSuitable(monthlyIncome,creditScore);

    assertEquals(FALSE, expected);

  }
}