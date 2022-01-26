package com.n11project.creditapplication.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CustomerDto {

    private String name;

    private String lastName;

    private Double monthlyIncome;

    private String identificationNumber;

    private String phoneNumber;

    private Date birthDate;

    private Double assurance;

    private Integer creditScore;


}
