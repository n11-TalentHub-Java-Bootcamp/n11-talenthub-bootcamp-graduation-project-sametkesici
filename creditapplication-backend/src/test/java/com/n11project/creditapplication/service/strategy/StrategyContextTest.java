package com.n11project.creditapplication.service.strategy;

import static com.n11project.creditapplication.model.ApplicationStatus.APPROVED;
import static com.n11project.creditapplication.model.ApplicationStatus.REJECTED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.n11project.creditapplication.model.Application;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StrategyContextTest {

  @Mock
  private List<CalculateLimitStrategy> calculateLimitStrategyList;

  @InjectMocks
  private StrategyContext strategyContext;

  @Test
  void shouldRejectApplication() {

    Double monthlyIncome = 10000.0;
    Double assurance = 0.0;
    Integer creditScore = 400;

    RejectStrategy rejectStrategy = new RejectStrategy();

    when(calculateLimitStrategyList.stream()).thenReturn(Stream.of(rejectStrategy));

    Application application = strategyContext.calculateLimitAndSetStatus(monthlyIncome, creditScore, assurance);

    assertEquals(REJECTED, application.getApplicationStatus());
  }

  @Test
  void shouldApprovedApplication() {

    Double monthlyIncome = 10500.0;
    Double assurance = 0.0;
    Integer creditScore = 1200;

    HighScoreStrategy highScoreStrategy = new HighScoreStrategy();

    when(calculateLimitStrategyList.stream()).thenReturn(Stream.of(highScoreStrategy));

    Application application = strategyContext.calculateLimitAndSetStatus(monthlyIncome, creditScore, assurance);

    assertEquals(APPROVED, application.getApplicationStatus());
  }
}