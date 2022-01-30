package com.n11project.creditapplication.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {

  @NotNull
  private Double monthlyIncome;

  @NotNull
  private String phoneNumber;

  private Double assurance;
}