package com.n11project.creditapplication.dto;

import java.util.Date;
import lombok.Data;

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