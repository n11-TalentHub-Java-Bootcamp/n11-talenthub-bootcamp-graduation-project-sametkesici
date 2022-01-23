package com.n11project.creditapplication.dto.request.application;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateApplicationRequest {

    private String name;

    private String lastName;

    private String identificationNumber;

    private BigDecimal monthlyIncome;

    private String phoneNumber;

    private Date birthDate;

    private BigDecimal assurance;

}
