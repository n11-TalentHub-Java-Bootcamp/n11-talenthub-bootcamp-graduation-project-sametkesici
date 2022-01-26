package com.n11project.creditapplication.strategy;


public interface CalculateLimitStrategy {

    Double calculateLimit(Double monthlyIncome , Double assurance);

    Boolean isSuitable(Double monthlyIncome, Integer creditScore);
}
