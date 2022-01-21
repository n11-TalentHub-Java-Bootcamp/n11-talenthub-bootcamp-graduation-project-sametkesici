package com.n11project.creditapplication.dto.request.application;

import lombok.Data;

import java.util.Date;

@Data
public class FindApplicationRequest {

    private String identificationNumber;

    private Date birthDate;

}
