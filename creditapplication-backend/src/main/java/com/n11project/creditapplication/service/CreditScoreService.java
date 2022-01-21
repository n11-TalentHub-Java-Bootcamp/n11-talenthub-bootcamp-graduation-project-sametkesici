package com.n11project.creditapplication.service;

import org.springframework.stereotype.Service;

import static com.n11project.creditapplication.util.CreditScoreUtils.generateRandomCreditScore;

@Service
public class CreditScoreService {

    public Integer getCreditScore(){
        return generateRandomCreditScore();
    }
}
