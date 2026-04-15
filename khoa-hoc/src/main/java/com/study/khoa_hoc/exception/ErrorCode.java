package com.study.khoa_hoc.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    //Không tồn tại(lỗi:404)
    USER_NOT_EXIST(40401, "User is not exists", HttpStatus.NOT_FOUND),
    PROFILE_NOT_EXIST(40402, "Profile is not exists", HttpStatus.NOT_FOUND),
    TEACHER_NOT_EXIST(40403, "Teacher is not exists", HttpStatus.NOT_FOUND),
    COURSE_NOT_EXIST(40403, "Teacher is not exists", HttpStatus.NOT_FOUND),

    //Vi phạm logic(lỗi:400)
    TEACHER_HAS_CLASSES(40001, "Cannot delete teacher because it is assigned to classes",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS(40002, "Email already exists", HttpStatus.BAD_REQUEST),
    PHONE_ALREADY_EXISTS(40003, "Phone number already exists", HttpStatus.BAD_REQUEST),
    COURSE_HAS_CLASSES(40004, "Course has classes, cannot delete", HttpStatus.BAD_REQUEST);

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
