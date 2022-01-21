package com.n11project.creditapplication.controller;

import com.n11project.creditapplication.dto.ApplicationDto;
import com.n11project.creditapplication.dto.request.application.FindApplicationRequest;
import com.n11project.creditapplication.mapper.application.ApplicationMapper;
import com.n11project.creditapplication.model.Application;
import com.n11project.creditapplication.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    private final ApplicationMapper applicationMapper;

    @GetMapping("")
    public ResponseEntity<ApplicationDto> findApplicationByIdentificationNumberAndBirthDate(@RequestBody FindApplicationRequest findApplicationRequest){
        Application application = applicationService.findApplicationByIdentificationNumberAndBirthDateOrThrowException(findApplicationRequest);
        ApplicationDto applicationDto = applicationMapper.toDto(application);
        return ResponseEntity.ok(applicationDto);
    }

}
