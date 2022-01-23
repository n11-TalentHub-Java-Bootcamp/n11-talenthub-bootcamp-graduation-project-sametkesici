package com.n11project.creditapplication.dto.request.application;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class FindApplicationRequest {

    @NotNull
    private String identificationNumber;

    @NotNull
    private Date birthDate;

}
