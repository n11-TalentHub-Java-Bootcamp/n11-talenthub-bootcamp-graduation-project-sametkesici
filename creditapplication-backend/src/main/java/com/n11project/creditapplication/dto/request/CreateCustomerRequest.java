package com.n11project.creditapplication.dto.request;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCustomerRequest {

  @NotNull
  private String name;

  @NotNull
  private String lastName;

  @NotNull
  private String identificationNumber;

  @NotNull
  private Double monthlyIncome;

  @NotNull
  private String phoneNumber;

  @NotNull
  private Date birthDate;

  @NotNull
  private Double assurance;
}
