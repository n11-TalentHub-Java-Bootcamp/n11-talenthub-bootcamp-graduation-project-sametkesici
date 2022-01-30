package com.n11project.creditapplication.controller;

import com.n11project.creditapplication.dto.response.ApplicationResponse;
import com.n11project.creditapplication.mapper.ApplicationResponseMapper;
import com.n11project.creditapplication.model.Application;
import com.n11project.creditapplication.service.ApplicationService;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

  private final ApplicationService applicationService;

  private final ApplicationResponseMapper applicationResponseMapper;

  @GetMapping
  public ResponseEntity<ApplicationResponse> findApplicationByIdentificationNumberAndBirthDate(
      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate,
      @RequestParam String identificationNumber) {
    Application application = applicationService.findApplicationByIdentificationNumberAndBirthDateOrThrowException(
        identificationNumber,
        birthDate);
    ApplicationResponse applicationResponse = applicationResponseMapper.toDto(application);
    log.info("Find application by date : {} and idNumber : {}", birthDate, identificationNumber);
    return ResponseEntity.ok(applicationResponse);
  }
}
