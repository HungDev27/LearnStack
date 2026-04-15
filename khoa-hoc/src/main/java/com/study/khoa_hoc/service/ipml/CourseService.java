package com.study.khoa_hoc.service.ipml;

import com.study.khoa_hoc.dto.request.CourseRequest;
import com.study.khoa_hoc.dto.response.CourseResponse;
import com.study.khoa_hoc.entity.Course;
import com.study.khoa_hoc.exception.ApiException;
import com.study.khoa_hoc.exception.ErrorCode;
import com.study.khoa_hoc.mapper.CourseMapper;
import com.study.khoa_hoc.repository.CourseRepository;
import com.study.khoa_hoc.service.ICourseService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseService implements ICourseService {

    CourseRepository courseRepository;
    CourseMapper courseMapper;

    @Override
    public List<CourseResponse> findAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(course -> courseMapper.toDto(course)).toList();
    }

    @Override
    public CourseResponse findById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.COURSE_NOT_EXIST));
        return courseMapper.toDto(course);
    }

    @Override
    public CourseResponse save(CourseRequest courseRequest) {
       Course course = courseMapper.toEntity(courseRequest);
       Course saved = courseRepository.save(course);
       return courseMapper.toDto(saved);
    }

    @Override
    public CourseResponse updateById(Long id, CourseRequest courseRequest) {
        Course course  = courseRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.COURSE_NOT_EXIST));
        courseMapper.updateEntity(course, courseRequest);
        courseRepository.save(course);
        return courseMapper.toDto(course);
    }

    @Override
    public void delete(Long id) {
        Course course  = courseRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.COURSE_NOT_EXIST));
        if (course.getClasses() != null && !course.getClasses().isEmpty()) {
            throw new ApiException(ErrorCode.COURSE_HAS_CLASSES);
        }
        courseRepository.deleteById(id);
    }
}
