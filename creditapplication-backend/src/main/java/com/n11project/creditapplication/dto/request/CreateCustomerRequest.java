package com.n11project.creditapplication.dto.request;

import javax.persistence.PrePersist;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @NotNull
    private Double assurance;

}
