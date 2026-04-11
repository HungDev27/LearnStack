package com.study.khoa_hoc.mapper;

import com.study.khoa_hoc.dto.request.ProfileRequest;
import com.study.khoa_hoc.dto.request.UserRequest;
import com.study.khoa_hoc.dto.response.ProfileResponse;
import com.study.khoa_hoc.dto.response.UserResponse;
import com.study.khoa_hoc.entity.Profile;
import com.study.khoa_hoc.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    // ========== METHOD 1: toResponse(User user) ==========
    // Map Entity User → DTO UserResponse
    // MapStruct tự động map các trường giống tên: id, name, email
    // @Mapping(source = "profile", target = "profileResponse")
    // chỉ dẫn map User.profile sang UserResponse.profileResponse
    // và gọi toResponse(Profile) để map nested object
    //
    // CODE MAP THỦ CÔNG:
    // public UserResponse toResponse(User user) {
    // if (user == null) return null;
    //
    // UserResponse res = new UserResponse();
    // res.setId(user.getId()); // map trường id
    // res.setName(user.getName()); // map trường name
    // res.setEmail(user.getEmail()); // map trường email
    //
    // // map nested object: profile → profileResponse
    // if (user.getProfile() != null) {
    // res.setProfileResponse(toResponse(user.getProfile())); // gọi
    // toResponse(Profile)
    // }
    //
    // return res;
    // }
    @Mapping(source = "profile", target = "profileResponse")
    UserResponse toResponse(User user);

    // ========== METHOD 2: toResponse(Profile profile) ==========
    // Map Entity Profile → DTO ProfileResponse
    // MapStruct tự động map: id, phone, address, avatar (tên giống)
    // Không cần @Mapping vì tên trường hoàn toàn giống
    //
    // CODE MAP THỦ CÔNG:
    // public ProfileResponse toResponse(Profile profile) {
    // if (profile == null) return null;
    //
    // ProfileResponse res = new ProfileResponse();
    // res.setId(profile.getId()); // map id
    // res.setPhone(profile.getPhone()); // map phone
    // res.setAddress(profile.getAddress()); // map address
    // res.setAvatar(profile.getAvatar()); // map avatar
    //
    // return res;
    // }
    ProfileResponse toResponse(Profile profile);

    // ========== METHOD 3: toEntity(UserRequest request) ==========
    // Map DTO Request → Entity User
    // @Mapping(target = "id", ignore = true) - không set id, để DB generate
    // @Mapping(target = "created_at", ignore = true) - không set,
    // @CreationTimestamp sẽ set
    // @Mapping(target = "updated_at", ignore = true) - không set, @UpdateTimestamp
    // sẽ set
    // @Mapping(target = "profile", source = "profileRequest")
    // - map UserRequest.profileRequest sang User.profile
    // - gọi toEntity(ProfileRequest) để tạo Profile entity
    //
    // CODE MAP THỦ CÔNG:
    // public User toEntity(UserRequest request) {
    // if (request == null) return null;
    //
    // User user = new User();
    // user.setId(null); // ignore - để DB generate
    // user.setName(request.getName()); // map name
    // user.setEmail(request.getEmail()); // map email
    // user.setPassword(request.getPassword()); // map password
    // user.setCreated_at(null); // ignore - @CreationTimestamp sẽ set
    // user.setUpdated_at(null); // ignore - @UpdateTimestamp sẽ set
    //
    // // map nested: profileRequest → profile
    // if (request.getProfileRequest() != null) {
    // Profile profile = toEntity(request.getProfileRequest()); // gọi
    // toEntity(ProfileRequest)
    // user.setProfile(profile); // gán profile vào user
    //
    // // ===== @AfterMapping logic (thiết lập quan hệ hai chiều) =====
    // if (user.getProfile() != null) {
    // user.getProfile().setUser(user); // profile.user = user
    // }
    // }
    //
    // return user;
    // }
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    @Mapping(target = "profile", source = "profileRequest")
    User toEntity(UserRequest request);

    // ========== METHOD 4: toEntity(ProfileRequest request) ==========
    // Map DTO ProfileRequest → Entity Profile
    // @Mapping(target = "id", ignore = true) - không set id, để DB generate
    // @Mapping(target = "created_at", ignore = true) - không set,
    // @CreationTimestamp sẽ set
    // @Mapping(target = "user", ignore = true) - không có trong ProfileRequest, sẽ
    // set bởi @AfterMapping
    //
    // CODE MAP THỦ CÔNG:
    // public Profile toEntity(ProfileRequest request) {
    // if (request == null) return null;
    //
    // Profile profile = new Profile();
    // profile.setId(null); // ignore - để DB generate
    // profile.setPhone(request.getPhone()); // map phone
    // profile.setAddress(request.getAddress()); // map address
    // profile.setAvatar(request.getAvatar()); // map avatar
    // profile.setCreated_at(null); // ignore - @CreationTimestamp sẽ set
    // profile.setUser(null); // ignore - sẽ set bởi @AfterMapping
    //
    // return profile;
    // }
    @Mapping(target = "id", ignore = true)   
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "user", ignore = true)
    Profile toEntity(ProfileRequest request);

    // ========== METHOD 5: @AfterMapping linkProfileUser() ==========
    // Thiết lập quan hệ hai chiều sau khi mapping từ UserRequest sang User
    // @AfterMapping - annotation đặc biệt của MapStruct, chạy tự động sau khi
    // mapping xong
    // @MappingTarget User user - tham số "đích" vừa được tạo/map xong
    //
    // TÁC DỤNG:
    // Khi toEntity(UserRequest) chạy xong, @AfterMapping tự động chạy và set
    // profile.user = user
    // Điều này đảm bảo quan hệ hai chiều: user.profile <-> profile.user
    //
    // CODE TỰ ĐỘNG CHẠY (không cần viết thủ công):
    // Sau khi User user = toEntity(UserRequest request) xong
    // MapStruct tự động gọi:
    // if (user.getProfile() != null) {
    // user.getProfile().setUser(user); // set quan hệ ngược lại
    // }
    @AfterMapping
    default void linkProfileUser(UserRequest request, @MappingTarget User user) {
        if (user.getProfile() != null) {
            user.getProfile().setUser(user);
        }
    }

    // ========== METHOD 6: updateFromRequest ==========
    // Update User từ UserRequest, nhưng KHÔNG tạo Profile mới
    // @Mapping(target = "profile", ignore = true) - bỏ qua profile
    // Profile sẽ được update riêng trong @AfterMapping
    //
    // CODE MAP THỦ CÔNG:
    // public void updateFromRequest(UserRequest request, User user) {
    // if (request == null || user == null) return;
    //
    // user.setName(request.getName());
    // user.setEmail(request.getEmail());
    // user.setPassword(request.getPassword());
    // // Không set profile, sẽ update riêng
    // }
    @Mapping(target = "profile", ignore = true)
    void updateFromRequest(UserRequest request, @MappingTarget User user);

    // ========== METHOD 6: updateProfileFromRequest ==========
    // Update Profile hiện có từ ProfileRequest
    // @MappingTarget Profile profile - update trực tiếp Profile đã tồn tại
    // Không tạo Profile mới, tránh duplicate key error
    //
    // CODE MAP THỦ CÔNG:
    // public void updateProfileFromRequest(ProfileRequest request, Profile profile)
    // {
    // if (request == null || profile == null) return;
    //
    // profile.setPhone(request.getPhone());
    // profile.setAddress(request.getAddress());
    // profile.setAvatar(request.getAvatar());
    // // Không set id, created_at, user (đã có)
    // }
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateProfileFromRequest(ProfileRequest request, @MappingTarget Profile profile);

    // ========== METHOD 7: @AfterMapping cho update ==========
    // Sau khi update User, update Profile hiện có thay vì tạo mới
    @AfterMapping
    default void updateProfileLink(UserRequest request, @MappingTarget User user) {
        if (request.getProfileRequest() != null && user.getProfile() != null) {
            updateProfileFromRequest(request.getProfileRequest(), user.getProfile());
        }
    }
}
