package com.study.khoa_hoc.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRequest {
    @NotBlank(message = "Tên khóa học không được để trống.")
    String title;

    String description;
    @Positive(message = "Thời lượng khóa học phải lớn hơn 0")
    Integer duration;

    @NotNull(message = "Giá không được để trống")
    @Positive(message = "Giá khóa học phải lớn hơn 0")
    Double price;
}
