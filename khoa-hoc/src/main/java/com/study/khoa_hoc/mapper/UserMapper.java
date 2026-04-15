package com.study.khoa_hoc.mapper;

import com.study.khoa_hoc.dto.request.UserRequest;
import com.study.khoa_hoc.dto.request.UserUpdateRequest;
import com.study.khoa_hoc.dto.response.UserResponse;
import com.study.khoa_hoc.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper( // Annotation chính của MapStruct, biến interface thành mapper tự động
        componentModel = "spring", // Tích hợp với Spring: MapStruct sẽ tạo bean Spring để inject vào
                                   // service/controller
                                    //MapStruct sẽ dùng mapper khác để hỗ trợ mapping object lồng nhau

        uses = { ProfileMapper.class }, // Sử dụng ProfileMapper cho các mapping liên quan đến Profile (ví dụ:
                                        // ProfileRequest -> Profile)

        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE // Bỏ qua mapping nếu giá trị nguồn
                                                                                   // là null (tránh ghi đè giá trị cũ
                                                                                   // bằng null)
)
public interface UserMapper { // Interface mapper - MapStruct sẽ tự generate implementation class
    @Mapping(target = "profile", source = "profileRequest") // Mapping cụ thể: field "profile" trong User <- field
                                                            // "profileRequest" trong UserRequest (sử dụng
                                                            // ProfileMapper)
    User toEntity(UserRequest userRequest); // Phương thức tạo User entity mới từ UserRequest DTO

    @Mapping(target = "profileResponse", source = "profile") // Mapping cụ thể: field "profileResponse" trong
                                                             // UserResponse <- field "profile" trong User (sử dụng
                                                             // ProfileMapper)
    UserResponse toDto(User user); // Phương thức chuyển User entity thành UserResponse DTO

    @Mapping(target = "profile", source = "profileRequest") // Mapping cụ thể: field "profile" trong User <- field
                                                            // "profileRequest" trong UserRequest (cho update)
    @Mapping(target = "id", ignore = true) // Bỏ qua mapping cho field "id" (không update id vì nó là khóa chính)
    void updateEntity(@MappingTarget User entity, UserRequest dto); // Phương thức update User entity hiện có từ
                                                                    // UserRequest (không trả về gì)

    @Mapping(target = "profile", source = "profileRequest")
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget User entity, UserUpdateRequest dto);
}
