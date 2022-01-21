package com.n11project.creditapplication.mapper.application;

import com.n11project.creditapplication.dto.request.application.CreateApplicationRequest;
import com.n11project.creditapplication.mapper.BaseMapper;
import com.n11project.creditapplication.model.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateApplicationMapper extends BaseMapper<Application,CreateApplicationRequest> {

}
