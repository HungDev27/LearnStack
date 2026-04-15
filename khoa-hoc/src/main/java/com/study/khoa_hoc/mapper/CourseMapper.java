package com.study.khoa_hoc.mapper;

import com.study.khoa_hoc.dto.request.CourseRequest;
import com.study.khoa_hoc.dto.response.CourseResponse;
import com.study.khoa_hoc.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {

    Course toEntity(CourseRequest courseRequest);

    CourseResponse toDto(Course course);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Course course, CourseRequest courseRequest );
}
