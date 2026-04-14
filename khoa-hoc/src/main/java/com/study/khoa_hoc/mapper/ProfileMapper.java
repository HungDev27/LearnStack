package com.study.khoa_hoc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.study.khoa_hoc.dto.request.ProfileRequest;
import com.study.khoa_hoc.dto.response.ProfileResponse;
import com.study.khoa_hoc.entity.Profile;

@Mapper(componentModel = "spring")  // Annotation MapStruct: Tích hợp Spring, tạo bean tự động (không có uses vì Profile không phụ thuộc mapper khác)
public interface ProfileMapper {  // Interface mapper cho Profile - MapStruct generate implementation
    Profile toEntity(ProfileRequest profileRequest);  // Tạo Profile entity mới từ ProfileRequest (mapping tự động nếu tên field giống)

    ProfileResponse toDto(Profile profile);  // Chuyển Profile entity thành ProfileResponse (mapping tự động nếu tên field giống)

    void updateEntity(@MappingTarget Profile entity, ProfileRequest dto);  // Update Profile entity từ ProfileRequest (mapping tự động, không ignore gì cụ thể)
}
