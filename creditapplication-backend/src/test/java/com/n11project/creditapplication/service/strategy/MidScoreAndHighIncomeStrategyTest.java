package com.n11project.creditapplication.service.strategy;

import static com.n11project.creditapplication.constants.CreditMultiplierConstant.CREDIT_LIMIT_MULTIPLIER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

import liquibase.pro.packaged.M;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MidScoreAndHighIncomeStrategyTest {


  MidScoreAndHighIncomeStrategy midScoreAndHighIncomeStrategy;

  @BeforeEach
  void setUp(){
    midScoreAndHighIncomeStrategy = new MidScoreAndHighIncomeStrategy();
  }

  @Test
  void shouldCalculateLimitCorrectly() {

    Double monthlyIncome = 10000.0;
    Double assurance = 5000.0;
    Double creditLimit = monthlyIncome * CREDIT_LIMIT_MULTIPLIER / 2 + assurance / 4;
    Double expectedCreditLimit = midScoreAndHighIncomeStrategy.calculateLimit(monthlyIncome,assurance);

    assertEquals(creditLimit,expectedCreditLimit);

  }

  @Test
  void shouldSuitForCalculate() {

    Double monthlyIncome = 10500.0;
    Integer creditScore  = 600;

    Boolean expected =  midScoreAndHighIncomeStrategy.isSuitable(monthlyIncome,creditScore);

    assertEquals(TRUE, expected);

  }

  @Test
  void shouldNotSuitForCalculate() {

    Double monthlyIncome = 10500.0;
    Integer creditScore  = 1100;

    Boolean expected =  midScoreAndHighIncomeStrategy.isSuitable(monthlyIncome,creditScore);

    assertEquals(FALSE, expected);

  }
}