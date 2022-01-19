package com.n11project.creditapplication.mapper;

import java.util.List;

public interface BaseMapper<T, U>{

    List<U> toDtoList(List<T> args);

    U toDto(T args);

}
