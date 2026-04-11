package com.study.khoa_hoc.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    USER_NOT_EXIST(40401, "User is not exists", HttpStatus.NOT_FOUND),
    COURSE_NOT_EXIST(40402, "Course is not exists", HttpStatus.NOT_FOUND);
    Integer code;
    String message;
    HttpStatus status;

}
