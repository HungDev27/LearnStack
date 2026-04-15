package com.study.khoa_hoc.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassesResponse {
    private Long id;
    private String className;
    private LocalDate startDate;
    private LocalDate  endDate;

    private String teacherName;
    private String courseName;
}
