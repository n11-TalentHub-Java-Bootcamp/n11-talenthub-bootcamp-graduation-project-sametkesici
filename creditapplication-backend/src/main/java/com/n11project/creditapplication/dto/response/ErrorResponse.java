package com.n11project.creditapplication.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {

  private String message;

  private String type;
}