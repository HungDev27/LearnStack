package com.study.khoa_hoc.service;

import com.study.khoa_hoc.dto.request.ProfileRequest;
import com.study.khoa_hoc.dto.response.ProfileResponse;

public interface IProfileService {
    public ProfileResponse getProfile(Long userId);
    public ProfileResponse updateProfile(Long userId, ProfileRequest profileRequest);
}
