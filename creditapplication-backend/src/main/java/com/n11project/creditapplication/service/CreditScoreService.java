package com.n11project.creditapplication.service;

import static com.n11project.creditapplication.util.CreditScoreUtils.generateRandomCreditScore;

import org.springframework.stereotype.Service;

@Service
public class CreditScoreService {

  public Integer getCreditScore() {
    return generateRandomCreditScore();
  }
}