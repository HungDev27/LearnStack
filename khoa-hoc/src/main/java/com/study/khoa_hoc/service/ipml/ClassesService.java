package com.study.khoa_hoc.service.ipml;

import com.study.khoa_hoc.dto.request.ClassesRequest;
import com.study.khoa_hoc.dto.request.CourseRequest;
import com.study.khoa_hoc.dto.response.ClassesDetailResponse;
import com.study.khoa_hoc.dto.response.ClassesResponse;
import com.study.khoa_hoc.dto.response.CourseResponse;
import com.study.khoa_hoc.entity.Classes;
import com.study.khoa_hoc.entity.Course;
import com.study.khoa_hoc.entity.Teacher;
import com.study.khoa_hoc.exception.ApiException;
import com.study.khoa_hoc.exception.ErrorCode;
import com.study.khoa_hoc.mapper.ClassesMapper;
import com.study.khoa_hoc.repository.ClassesRepository;
import com.study.khoa_hoc.repository.CourseRepository;
import com.study.khoa_hoc.repository.TeacherRepository;
import com.study.khoa_hoc.service.IClassesService;
import com.study.khoa_hoc.service.ICourseService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ClassesService implements IClassesService {

    ClassesRepository classesRepository;
    ClassesMapper classesMapper;
    CourseRepository courseRepository;
    TeacherRepository teacherRepository;

    @Override
    public List<ClassesResponse> findALl() {
        List<Classes> classes = classesRepository.findAllWithRelation();
        return classes.stream().map(classesMapper::toDto).toList();
    }

    @Override
    public ClassesDetailResponse findById(Long id) {
        Classes classes = classesRepository.findByIdWithRelation(id).orElseThrow(()-> new ApiException(ErrorCode.CLASSES_NOT_EXIST));
        return classesMapper.toDetailDto(classes);
    }

    @Override
    public ClassesResponse save(ClassesRequest classesRequest) {
        Classes classes  = classesMapper.toEntity(classesRequest);

        // 2. load dữ liệu thật từ DB
        Course course = courseRepository.findById(classesRequest.getCourseId())
                .orElseThrow(() -> new ApiException(ErrorCode.COURSE_NOT_EXIST));

        Teacher teacher = teacherRepository.findById(classesRequest.getTeacherId())
                .orElseThrow(() -> new ApiException(ErrorCode.TEACHER_NOT_EXIST));

        classes.setCourse(course);
        classes.setTeacher(teacher);

        Classes saved = classesRepository.save(classes);

        return classesMapper.toDto(saved);
    }

    @Override
    public ClassesResponse update(Long id, ClassesRequest classesRequest) {
        Classes classes = classesRepository.findByIdWithRelation(id).orElseThrow(()-> new ApiException(ErrorCode.CLASSES_NOT_EXIST));
        classesMapper.updateEntity(classes,classesRequest);
        return classesMapper.toDto(classes);
    }

    @Override
    public void delete(Long id) {
        Classes classes = classesRepository.findByIdWithRelation(id).orElseThrow(()-> new ApiException(ErrorCode.CLASSES_NOT_EXIST));

        if (classes.getEnrollments() != null && !classes.getEnrollments().isEmpty()) {
            throw new ApiException(ErrorCode.CLASS_HAS_ENROLLMENTS);
        }

        classesRepository.deleteById(id);
    }
}
