package com.study.khoa_hoc.service.ipml;

import com.study.khoa_hoc.dto.request.ProfileRequest;
import com.study.khoa_hoc.dto.response.ProfileResponse;
import com.study.khoa_hoc.entity.Profile;
import com.study.khoa_hoc.entity.User;
import com.study.khoa_hoc.exception.ApiException;
import com.study.khoa_hoc.exception.ErrorCode;
import com.study.khoa_hoc.mapper.ProfileMapper;
import com.study.khoa_hoc.repository.ProfileRepository;
import com.study.khoa_hoc.repository.UserRepository;
import com.study.khoa_hoc.service.IProfileService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProfileSevice implements IProfileService {

    ProfileRepository profileRepository;
    ProfileMapper profileMapper;
    UserRepository userRepository;

    @Override
    public ProfileResponse getProfile(Long userId) {
        // Bỏ chữ Optional<>, lấy trực tiếp Entity Profile
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiException(ErrorCode.PROFILE_NOT_EXIST));

        return profileMapper.toDto(profile);
    }

    @Override
    public ProfileResponse updateProfile(Long userId, ProfileRequest request) {
        // 1. Kiểm tra User có tồn tại không
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_EXIST));

        // 2. Tìm Profile, nếu chưa có thì tạo mới (vì có thể khi đăng ký họ chưa nhập profile)
        Profile profile = profileRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Profile newProfile = new Profile();
                    newProfile.setUser(user);
                    return newProfile;
                });

        // 3. Sử dụng MapStruct để cập nhật dữ liệu từ Request vào Entity
        profileMapper.updateEntity(profile, request);

        // 4. Lưu lại
        return profileMapper.toDto(profileRepository.save(profile));
    }
}
