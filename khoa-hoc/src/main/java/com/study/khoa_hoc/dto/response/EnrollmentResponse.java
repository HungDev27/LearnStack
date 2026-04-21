package com.study.khoa_hoc.dto.response;


import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponse {
    private Long id;
    private LocalDate enrollmentDate;
    private Integer finalScore;

    private String userName;
    private String className;
}
