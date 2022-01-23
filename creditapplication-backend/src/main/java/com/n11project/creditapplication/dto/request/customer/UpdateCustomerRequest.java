package com.n11project.creditapplication.dto.request.customer;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class UpdateCustomerRequest {

    @NotNull
    private BigDecimal monthlyIncome;

    @NotNull
    private String phoneNumber;

    @NotNull
    private BigDecimal assurance;

}
