package com.n11project.creditapplication.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class FindApplicationRequest {

    @NotNull
    private String identificationNumber;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

}
