package com.study.khoa_hoc.service;

import com.study.khoa_hoc.dto.request.CourseRequest;
import com.study.khoa_hoc.dto.response.CourseResponse;
import com.study.khoa_hoc.entity.Course;

import java.util.List;

public interface ICourseService {
    List<CourseResponse> findAll();
    CourseResponse findById(Long id);
    CourseResponse save(CourseRequest courseRequest);
    CourseResponse updateById(Long id, CourseRequest courseRequest);
    void delete(Long id);

}
