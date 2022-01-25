package com.n11project.creditapplication.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {

    @NotNull
    private BigDecimal monthlyIncome;

    @NotNull
    private String phoneNumber;

    private BigDecimal assurance;

}
