package com.study.khoa_hoc.mapper;

import com.study.khoa_hoc.dto.request.ClassesRequest;
import com.study.khoa_hoc.dto.response.ClassesDetailResponse;
import com.study.khoa_hoc.dto.response.ClassesResponse;
import com.study.khoa_hoc.entity.Classes;
import com.study.khoa_hoc.entity.Course;
import com.study.khoa_hoc.entity.Teacher;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ClassesMapper {

    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "course", source = "courseId",qualifiedByName = "mapCourse")
    @Mapping(target = "teacher", source = "teacherId",qualifiedByName = "mapTeacher")
    Classes toEntity(ClassesRequest classesRequest);

    @Mapping(target = "teacherName", source = "teacher.name")
    @Mapping(target = "courseTitle", source = "course.title")
    ClassesResponse toDto(Classes classes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "course", source = "courseId",qualifiedByName = "mapCourse")
    @Mapping(target = "teacher", source = "teacherId",qualifiedByName = "mapTeacher")
    void updateEntity(@MappingTarget Classes classes, ClassesRequest classesRequest);

    @Mapping(target = "teacher", source = "teacher")
    @Mapping(target = "course", source = "course")
    ClassesDetailResponse toDetailDto(Classes classes);

    // ===== helper convert =====
    @Named("mapCourse")
    default Course mapCourse(Long id) {
        if (id == null) return null;
        Course c = new Course();
        c.setId(id);
        return c;
    }

    @Named("mapTeacher")
    default Teacher mapTeacher(Long id) {
        if (id == null) return null;
        Teacher t = new Teacher();
        t.setId(id);
        return t;
    }
}
