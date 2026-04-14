package com.study.khoa_hoc.service;

import com.study.khoa_hoc.dto.request.TeacherRequest;
import com.study.khoa_hoc.dto.request.UserUpdateRequest;
import com.study.khoa_hoc.dto.response.TeacherResponse;
import com.study.khoa_hoc.dto.response.UserResponse;

import java.util.List;

public interface ITeacherService {

    List<TeacherResponse> findAll();

    TeacherResponse findById(Long id);

    TeacherResponse save(TeacherRequest teacherRequest);

    TeacherResponse updateById(Long id, TeacherRequest teacherRequest);

    void deleteById(Long id);

}
