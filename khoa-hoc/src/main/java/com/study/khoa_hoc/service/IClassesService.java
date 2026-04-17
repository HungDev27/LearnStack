package com.study.khoa_hoc.service;

import com.study.khoa_hoc.dto.request.ClassesRequest;
import com.study.khoa_hoc.dto.response.ClassesDetailResponse;
import com.study.khoa_hoc.dto.response.ClassesResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClassesService {

    List<ClassesResponse> findALl();

    ClassesDetailResponse findById(Long id);

    ClassesResponse save(ClassesRequest classesRequest);

    ClassesResponse update(Long id, ClassesRequest classesRequest);
    void delete(Long id);
}
