package com.study.khoa_hoc.service;

import com.study.khoa_hoc.dto.request.EnrollmentRequest;
import com.study.khoa_hoc.dto.request.UpdateScoreRequest;
import com.study.khoa_hoc.dto.response.EnrollmentResponse;

import java.util.List;

public interface IEnrollmentService {

    //Lấy tất cả enrollment
    List<EnrollmentResponse> findALl();

    //Đăng ký tham gia Lớp học (Create Enrollment)
    EnrollmentResponse save(EnrollmentRequest enrollmentRequest);

    //Cập nhật Điểm số (Update Final Score)
    EnrollmentResponse update(Long id, UpdateScoreRequest request);

    //Xem danh sách Học viên của một Lớp học (Get Enrollments by Class)
    List<EnrollmentResponse> findByUser(Long userId);

    //Xem danh sách Học viên của một Lớp học (Get Enrollments by Class)
    List<EnrollmentResponse> findByClass(Long classId);

    // Hủy đăng ký
    void delete(Long id);

}
