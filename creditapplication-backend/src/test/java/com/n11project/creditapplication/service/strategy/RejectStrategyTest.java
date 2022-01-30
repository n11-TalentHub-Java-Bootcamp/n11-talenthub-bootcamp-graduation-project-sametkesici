package com.n11project.creditapplication.service.strategy;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RejectStrategyTest {

  @InjectMocks
  private RejectStrategy rejectStrategy;

  @Test
  void shouldReturnLimitZero(){
    Double expectedCreditLimit = rejectStrategy.calculateLimit(1000.0,0.0);
    assertEquals(0,expectedCreditLimit);
  }

  @Test
  void shouldSuitForCalculate() {

    Double monthlyIncome = 10500.0;
    Integer creditScore  = 100;

    Boolean expected =  rejectStrategy.isSuitable(monthlyIncome,creditScore);

    assertEquals(TRUE, expected);

  }

  @Test
  void shouldNotSuitForCalculate() {

    Double monthlyIncome = 10500.0;
    Integer creditScore  = 1100;

    Boolean expected =  rejectStrategy.isSuitable(monthlyIncome,creditScore);

    assertEquals(FALSE, expected);

  }
}