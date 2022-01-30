package com.n11project.creditapplication.dto.response;

import com.n11project.creditapplication.model.ApplicationStatus;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ApplicationResponse {

  private BigDecimal creditLimit;

  private ApplicationStatus applicationStatus;
}