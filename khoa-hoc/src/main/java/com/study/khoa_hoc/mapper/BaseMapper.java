package com.study.khoa_hoc.mapper;

import java.util.List;

public interface BaseMapper<D, E> {  // D: DTO, E: Entity
    E toEntity(D dto);              // Chuyển DTO thành Entity
    D toDto(E entity);              // Chuyển Entity thành DTO
    List<E> toEntity(List<D> dtoList);  // Chuyển list DTO thành list Entity
    List<D> toDto(List<E> entityList);  // Chuyển list Entity thành list DTO
}
