package com.n11project.creditapplication.controller;

import com.n11project.creditapplication.dto.response.ApplicationResponse;
import com.n11project.creditapplication.mapper.ApplicationResponseMapper;
import com.n11project.creditapplication.model.Application;
import com.n11project.creditapplication.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;


@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    private final ApplicationResponseMapper applicationResponseMapper;


    @GetMapping
    public ResponseEntity<ApplicationResponse> findApplicationByIdentificationNumberAndBirthDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate ,@RequestParam String identificationNumber){
        Application application = applicationService.findApplicationByIdentificationNumberAndBirthDateOrThrowException(identificationNumber, birthDate);
        ApplicationResponse applicationResponse = applicationResponseMapper.toDto(application);
        return ResponseEntity.ok(applicationResponse);
    }




}
