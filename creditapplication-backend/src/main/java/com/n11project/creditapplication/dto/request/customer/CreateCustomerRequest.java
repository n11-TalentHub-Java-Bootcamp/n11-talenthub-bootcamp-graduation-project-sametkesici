package com.n11project.creditapplication.dto.request.customer;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateCustomerRequest {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String identificationNumber;

    @NotNull
    private BigDecimal monthlyIncome;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Date birthDate;

    @NotNull
    private BigDecimal assurance;
}
