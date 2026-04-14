package com.study.khoa_hoc.controller;

import com.study.khoa_hoc.dto.ApiResponse;
import com.study.khoa_hoc.dto.request.ProfileRequest;
import com.study.khoa_hoc.dto.response.ProfileResponse;
import com.study.khoa_hoc.service.IProfileService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileController {

    IProfileService profileService;

    @GetMapping("/{user_id}")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(@PathVariable Long user_id) {
        return ResponseEntity.ok(ApiResponse.<ProfileResponse>builder()
                .code(2000)
                .message("Lấy thông tin thành công.")
                .data(profileService.getProfile(user_id))
                .build());
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<ApiResponse<ProfileResponse>> updateProfile(
            @PathVariable Long user_id,
            @Valid @RequestBody ProfileRequest request) {
        return ResponseEntity.ok(ApiResponse.<ProfileResponse>builder()
                .code(2000)
                .message("Cập nhật thông tin thành công.")
                .data(profileService.updateProfile(user_id, request))
                .build());
    }
}
