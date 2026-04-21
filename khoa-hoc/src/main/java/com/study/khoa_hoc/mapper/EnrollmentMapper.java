package com.study.khoa_hoc.mapper;


import com.study.khoa_hoc.dto.request.EnrollmentRequest;
import com.study.khoa_hoc.dto.response.EnrollmentResponse;
import com.study.khoa_hoc.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EnrollmentMapper {
    // 👉 Request → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)      // xử lý ở service
    @Mapping(target = "classes", ignore = true)   // xử lý ở service
    @Mapping(target = "finalScore", ignore = true)
    @Mapping(target = "enrollmentDate", ignore = true)
    Enrollment toEntity(EnrollmentRequest request);

    // 👉 Entity → Response
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "className", source = "classes.className")
    EnrollmentResponse toDto(Enrollment enrollment);
}
