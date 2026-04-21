package com.study.khoa_hoc.controller;

import com.study.khoa_hoc.dto.ApiResponse;
import com.study.khoa_hoc.dto.request.EnrollmentRequest;
import com.study.khoa_hoc.dto.request.UpdateScoreRequest;
import com.study.khoa_hoc.dto.response.EnrollmentResponse;
import com.study.khoa_hoc.service.IEnrollmentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EnrollmentController {
    IEnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<?> addEnrollment(@Valid @RequestBody EnrollmentRequest enrollmentRequest){
        EnrollmentResponse response = enrollmentService.save(enrollmentRequest);
        return ResponseEntity.ok(ApiResponse.<EnrollmentResponse>builder()
                .data(response)
                .build());
    }
    @PutMapping("/{id}/score")
    public ApiResponse<EnrollmentResponse> updateScore( @PathVariable Long id,@RequestBody UpdateScoreRequest request) {
        EnrollmentResponse result = enrollmentService.update(id, request);

        return ApiResponse.<EnrollmentResponse>builder()
                .data(result)
                .message("Update score successfully")
                .build();
    }

    @GetMapping("/users/{userId}")
    public ApiResponse<List<EnrollmentResponse>> getByUser(@PathVariable Long userId) {

        List<EnrollmentResponse> result = enrollmentService.findByUser(userId);

        return ApiResponse.<List<EnrollmentResponse>>builder()
                .data(result)
                .message("Lấy danh sách khóa học thành công")
                .build();
    }

    @GetMapping("/classes/{id}")
    public ApiResponse<List<EnrollmentResponse>> getByClasses(@PathVariable Long id) {

        List<EnrollmentResponse> result = enrollmentService.findByClass(id);

        return ApiResponse.<List<EnrollmentResponse>>builder()
                .data(result)
                .message("Lấy danh sách lớp học thành công")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteEnrollment(@PathVariable Long id) {

        enrollmentService.delete(id);

        return ApiResponse.<Void>builder()
                .message("Hủy đăng ký lớp học thành công")
                .build();
    }
}
