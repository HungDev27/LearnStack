package com.study.khoa_hoc.service.ipml;

import com.study.khoa_hoc.dto.request.EnrollmentRequest;
import com.study.khoa_hoc.dto.request.UpdateScoreRequest;
import com.study.khoa_hoc.dto.response.EnrollmentResponse;
import com.study.khoa_hoc.entity.Classes;
import com.study.khoa_hoc.entity.Enrollment;
import com.study.khoa_hoc.entity.User;
import com.study.khoa_hoc.exception.ApiException;
import com.study.khoa_hoc.exception.ErrorCode;
import com.study.khoa_hoc.mapper.EnrollmentMapper;
import com.study.khoa_hoc.repository.ClassesRepository;
import com.study.khoa_hoc.repository.EnrollmentRepository;
import com.study.khoa_hoc.repository.UserRepository;
import com.study.khoa_hoc.service.IEnrollmentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EnrollmentService implements IEnrollmentService {

    EnrollmentRepository enrollmentRepository;
    EnrollmentMapper enrollmentMapper;

    UserRepository userRepository;
    ClassesRepository classesRepository;

    @Override
    public List<EnrollmentResponse> findALl() {
        return List.of();
    }

    @Override
    public EnrollmentResponse save(EnrollmentRequest request) {

        //  1. map basic
        Enrollment enrollment = enrollmentMapper.toEntity(request);
        //  2. fetch user: lấy id của user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_EXIST));
        //  3. fetch class:lấy id của classes
        Classes classes = classesRepository.findById(request.getClassId())
                .orElseThrow(() -> new ApiException(ErrorCode.CLASSES_NOT_EXIST));

        // 4. Kiểm tra xem user đang đăng ký classes chưa(user chỉ đk được 1 classes)
        if (enrollmentRepository.existsByUser_IdAndClasses_Id(
                request.getUserId(), request.getClassId())) {
            throw new ApiException(ErrorCode.ALREADY_ENROLLED);
        }
        // set lại (QUAN TRỌNG)
        enrollment.setUser(user);
        enrollment.setClasses(classes);

        Enrollment saved = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDto(saved);
    }

    @Override
    public EnrollmentResponse update(Long id, UpdateScoreRequest request) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.ENROLLMENT_NOT_FOUND)); // 1. tìm enrollment

        // 2. validate điểm
        Integer finalScore = request.getFinalScore();
        if (finalScore == null || finalScore < 0 || finalScore > 100) {
            throw new ApiException(ErrorCode.INVALID_SCORE);
        }

        // 3. check lớp đã kết thúc chưa
        LocalDate endDate = enrollment.getClasses().getEndDate();
        if (endDate == null) {
            throw new ApiException(ErrorCode.CLASS_DATE_INVALID);
        }
        if (endDate.isAfter(LocalDate.now())) {
            throw new ApiException(ErrorCode.CLASS_NOT_ENDED);
        }

        // 4. set điểm
        enrollment.setFinalScore(finalScore);

        // 5. save
        Enrollment updated = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDto(updated);
    }

    //GET /api/enrollments/users/{userId}
    @Override
    public List<EnrollmentResponse> findByUser(Long userId) {
        // 1. check user tồn tại
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_EXIST));

        // 2. lấy danh sách enrollment theo user
        List<Enrollment> enrollments = enrollmentRepository.findByUser_Id(userId);

        // 3. map sang DTO
        return enrollments.stream()
                .map(enrollmentMapper::toDto)
                .toList();
    }

    @Override
    public List<EnrollmentResponse> findByClass(Long classId) {
        Classes classes = classesRepository.findById(classId)
                .orElseThrow(() -> new ApiException(ErrorCode.CLASSES_NOT_EXIST));
        List<Enrollment> enrollments = enrollmentRepository.findByClasses_Id(classId);
        return enrollments.stream()
                .map(enrollmentMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {

        // 1. tìm enrollment
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.ENROLLMENT_NOT_FOUND));

        // 2. lấy lớp
        Classes classes = enrollment.getClasses();

        // 3. check ngày bắt đầu
        if (classes.getStartDate() == null) {
            throw new ApiException(ErrorCode.CLASS_DATE_INVALID);
        }

        if (!classes.getStartDate().isAfter(LocalDate.now())) {
            // lớp đã bắt đầu hoặc đang diễn ra
            throw new ApiException(ErrorCode.CLASS_ALREADY_STARTED);
        }

        // 4. cho phép hủy
        enrollmentRepository.delete(enrollment);
    }


}
