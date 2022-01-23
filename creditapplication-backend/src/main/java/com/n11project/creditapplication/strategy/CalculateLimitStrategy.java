package com.n11project.creditapplication.strategy;

import java.math.BigDecimal;

public interface CalculateLimitStrategy {

    BigDecimal calculateLimit(BigDecimal monthlyIncome , BigDecimal assurance);

    Boolean isSuitable(BigDecimal monthlyIncome, Integer creditScore);
}
