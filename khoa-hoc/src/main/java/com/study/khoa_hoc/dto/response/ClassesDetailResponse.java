package com.study.khoa_hoc.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassesDetailResponse {
    private Long id;
    private String className;
    private LocalDate startDate;
    private LocalDate endDate;

    private TeacherResponse teacher;
    private CourseResponse course;
}
