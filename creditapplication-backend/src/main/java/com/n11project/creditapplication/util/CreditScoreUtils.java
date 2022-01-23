package com.n11project.creditapplication.util;

import java.util.Random;

public final class CreditScoreUtils {

    private CreditScoreUtils(){
        throw new UnsupportedOperationException("This is a constant class and cannot be instantiated");
    }

    public static Integer generateRandomCreditScore(){
        Random random = new Random();
        int highestCreditScore = 1500;
        return random.nextInt(highestCreditScore);
    }
}
