package com.n11project.creditapplication.mapper;

import com.n11project.creditapplication.dto.response.ApplicationResponse;
import com.n11project.creditapplication.model.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationResponseMapper extends BaseMapper<Application, ApplicationResponse> {}