package com.study.khoa_hoc.mapper;

import com.study.khoa_hoc.dto.request.ClassesRequest;
import com.study.khoa_hoc.dto.request.CourseRequest;
import com.study.khoa_hoc.dto.response.ClassesResponse;
import com.study.khoa_hoc.entity.Classes;
import com.study.khoa_hoc.entity.Course;
import com.study.khoa_hoc.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClassesMapper {

    @Mapping(target = "course", source = "courseId")
    @Mapping(target = "teacher", source = "teacherId")
    Classes toEntity(ClassesRequest classesRequest);

    ClassesResponse toDto(Classes classes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", source = "courseId")
    @Mapping(target = "teacher", source = "teacherId")
    void updateEntity(@MappingTarget Classes classes, ClassesRequest classesRequest);

    // ===== helper convert =====

    default Course mapCourse(Long id) {
        if (id == null) return null;
        Course c = new Course();
        c.setId(id);
        return c;
    }

    default Teacher mapTeacher(Long id) {
        if (id == null) return null;
        Teacher t = new Teacher();
        t.setId(id);
        return t;
    }
}
