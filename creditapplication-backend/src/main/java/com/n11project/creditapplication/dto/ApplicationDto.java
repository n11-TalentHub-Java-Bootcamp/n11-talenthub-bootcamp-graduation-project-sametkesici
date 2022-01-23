package com.n11project.creditapplication.dto;

import com.n11project.creditapplication.model.ApplicationStatus;
import java.math.BigDecimal;

public class ApplicationDto {

    private BigDecimal currentCreditLimit;

    private ApplicationStatus applicationStatus;

    private Long customerId;

}
