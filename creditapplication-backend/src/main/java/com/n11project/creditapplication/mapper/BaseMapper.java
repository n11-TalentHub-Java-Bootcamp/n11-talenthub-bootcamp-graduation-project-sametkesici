package com.n11project.creditapplication.mapper;

import java.util.List;

public interface BaseMapper<T, U> {

  T toEntity(U args);

  List<T> toEntityList(List<U> args);

  List<U> toDtoList(List<T> args);

  U toDto(T args);
}