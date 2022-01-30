package com.n11project.creditapplication.dto.request;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class FindApplicationRequest {

  @NotNull
  private String identificationNumber;

  @NotNull
  private Date birthDate;
}