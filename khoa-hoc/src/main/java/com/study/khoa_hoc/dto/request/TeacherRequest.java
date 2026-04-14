package com.study.khoa_hoc.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherRequest {
    @NotBlank(message = "Tên không được để trống.")
    String name;

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải có 10 số và bắt đầu bằng số 0.")
    String sdt;

    @NotBlank(message = "Email không được để trống.")
    @Email(message = "Định dạng Email không hợp lệ.")
    String email;
}
