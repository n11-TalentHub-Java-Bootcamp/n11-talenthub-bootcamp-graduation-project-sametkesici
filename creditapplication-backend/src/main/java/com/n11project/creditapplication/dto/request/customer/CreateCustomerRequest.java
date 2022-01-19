package com.n11project.creditapplication.dto.request.customer;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateCustomerRequest {

    private String name;

    private String lastName;

    private BigDecimal monthlyIncome;

    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private BigDecimal assurance;

}
