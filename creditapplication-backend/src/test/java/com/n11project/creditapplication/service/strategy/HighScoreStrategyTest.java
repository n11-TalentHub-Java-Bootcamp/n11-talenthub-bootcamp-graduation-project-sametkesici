package com.n11project.creditapplication.service.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.n11project.creditapplication.constant.CreditMultiplierConstant.CREDIT_LIMIT_MULTIPLIER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class HighScoreStrategyTest {


  @Mock
  private HighScoreStrategy highScoreStrategy;

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