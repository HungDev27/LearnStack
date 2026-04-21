package com.study.khoa_hoc.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    // Không tồn tại (lỗi: 404)
    USER_NOT_EXIST(40401, "Người dùng không tồn tại", HttpStatus.NOT_FOUND),
    PROFILE_NOT_EXIST(40402, "Hồ sơ không tồn tại", HttpStatus.NOT_FOUND),
    TEACHER_NOT_EXIST(40403, "Giảng viên không tồn tại", HttpStatus.NOT_FOUND),
    COURSE_NOT_EXIST(40404, "Khóa học không tồn tại", HttpStatus.NOT_FOUND),
    CLASSES_NOT_EXIST(40405, "Lớp học không tồn tại", HttpStatus.NOT_FOUND),
    ENROLLMENT_NOT_FOUND(40406, "Không tìm thấy đăng ký học", HttpStatus.NOT_FOUND),

    // Vi phạm logic (lỗi: 400)
    TEACHER_HAS_CLASSES(40001, "Không thể xóa giảng viên vì đang được gán cho lớp học", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS(40002, "Email đã tồn tại", HttpStatus.BAD_REQUEST),
    PHONE_ALREADY_EXISTS(40003, "Số điện thoại đã tồn tại", HttpStatus.BAD_REQUEST),
    COURSE_HAS_CLASSES(40004, "Khóa học đã có lớp, không thể xóa", HttpStatus.BAD_REQUEST),
    CLASS_HAS_ENROLLMENTS(40005, "Lớp học đã có học viên đăng ký, không thể xóa", HttpStatus.BAD_REQUEST),
    ALREADY_ENROLLED(40006, "Người dùng đã đăng ký lớp học này", HttpStatus.BAD_REQUEST),
    INVALID_SCORE(40011, "Điểm phải nằm trong khoảng từ 0 đến 100", HttpStatus.BAD_REQUEST),
    CLASS_NOT_ENDED(40012, "Lớp học chưa kết thúc", HttpStatus.BAD_REQUEST),
    CLASS_DATE_INVALID(40013, "Ngày kết thúc lớp học không hợp lệ hoặc bị thiếu", HttpStatus.BAD_REQUEST),
    CLASS_ALREADY_STARTED(40014, "Lớp học đã bắt đầu, không thể hủy đăng ký", HttpStatus.BAD_REQUEST);

    Integer code;
    String message;
    HttpStatus status;

    ErrorCode(Integer code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
