package com.n11project.creditapplication.service;


import com.n11project.creditapplication.util.CreditScoreUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class CreditScoreServiceTest {


  @InjectMocks
  private CreditScoreService creditScoreService;

  @Test
  void getCreditScore() {


    try (MockedStatic<CreditScoreUtils> theMock = Mockito.mockStatic(CreditScoreUtils.class)) {
      theMock.when(CreditScoreUtils::generateRandomCreditScore)
             .thenReturn(100);

      Integer expectedCreditScore = creditScoreService.getCreditScore();

      assertEquals(100, expectedCreditScore);
    }
  }
}