package com.study.khoa_hoc.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotBlank(message = "Tên không ược để trống.")
    @Size(min = 2, max = 50, message = "Tên phải từ 2 đến 50 ký tự.")
    String name;

    @NotBlank(message = "Email không được để trống.")
    @Email(message = "Định dạng Email không hợp lệ.")
    String email;

    @NotBlank(message = "Mật khẩu không được để trống.")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự.")
    String password;

     @Valid
    ProfileRequest profileRequest;
}
