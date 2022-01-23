package com.n11project.creditapplication.constants;

import java.math.BigDecimal;

public final class CreditMultiplierConstant {

    private CreditMultiplierConstant(){
        throw new UnsupportedOperationException("This is a constant class and cannot be instantiated");
    }

    public static final BigDecimal CREDIT_LIMIT_MULTIPLIER = BigDecimal.valueOf(4);

}
