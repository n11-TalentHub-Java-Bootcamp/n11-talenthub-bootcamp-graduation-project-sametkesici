package com.n11project.creditapplication.dto.request.customer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateCustomerRequest {

    private String name;

    private String lastName;

    private String identificationNumber;

    private BigDecimal monthlyIncome;

    private String phoneNumber;

    private Date birthDate;

    private BigDecimal assurance;
}
