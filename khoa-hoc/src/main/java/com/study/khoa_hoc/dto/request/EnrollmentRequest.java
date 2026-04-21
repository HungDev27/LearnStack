package com.study.khoa_hoc.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentRequest {

    private Long userId;     // sinh viên nào đăng ký
    private Long classId; // đăng ký lớp nào

    //private LocalDate enrollmentDate; // optional (có thể auto ở BE)
}