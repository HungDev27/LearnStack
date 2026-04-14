package com.study.khoa_hoc.mapper;


import com.study.khoa_hoc.dto.request.TeacherRequest;
import com.study.khoa_hoc.dto.request.UserRequest;
import com.study.khoa_hoc.dto.response.TeacherResponse;
import com.study.khoa_hoc.entity.Teacher;
import com.study.khoa_hoc.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeacherMapper {

    Teacher toEntity(TeacherRequest teacherRequest);

    TeacherResponse toDto(Teacher teacher);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Teacher entity, TeacherRequest dto);
}
