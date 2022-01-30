package com.n11project.creditapplication.service.strategy;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

import liquibase.pro.packaged.E;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MidScoreStrategyTest {

  @Mock
  private MidScoreStrategy midScoreStrategy;


  @Test
  void shouldNotSuitForCalculate() {

    Double monthlyIncome = 10500.0;
    Integer creditScore = 400;
    Boolean expected = midScoreStrategy.isSuitable(monthlyIncome, creditScore);
    assertEquals(FALSE, expected);
  }
}