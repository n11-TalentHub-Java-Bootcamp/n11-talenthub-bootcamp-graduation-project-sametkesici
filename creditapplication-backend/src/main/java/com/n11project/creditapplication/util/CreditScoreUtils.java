package com.n11project.creditapplication.util;

import java.util.Random;

public final class CreditScoreUtils {

    public static Integer generateCreditScore(){
        Random random = new Random();
        int highestCreditScore = 1500;

        return random.nextInt(highestCreditScore);
    }
}
