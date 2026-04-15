package com.study.khoa_hoc.mapper;

import java.util.List;

public interface BaseMapper<REQ, RES, E> {

    // 1. Request → Entity
    E toEntity(REQ request);

    // 2. Entity → Response
    RES toDto(E entity);

    // 3. List Request → List Entity
    //List<E> toEntityList(List<REQ> requestList);

    // 4. List Entity → List Response
    //List<RES> toDtoList(List<E> entityList);
}
