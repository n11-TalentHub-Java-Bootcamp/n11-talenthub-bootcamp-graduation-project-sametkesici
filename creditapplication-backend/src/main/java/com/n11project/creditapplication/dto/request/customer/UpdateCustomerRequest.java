package com.n11project.creditapplication.dto.request.customer;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class UpdateCustomerRequest {

    private BigDecimal monthlyIncome;

    private String phoneNumber;

    private BigDecimal assurance;

}
