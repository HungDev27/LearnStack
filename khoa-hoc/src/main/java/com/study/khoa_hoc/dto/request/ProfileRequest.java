package com.study.khoa_hoc.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileRequest {

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải có 10 số và bắt đầu bằng số 0.")
    String phone;

    @Size(min = 2, max = 20, message = "địa chỉ phải từ 2 đến 20 ký tự.")
    String address;

    @NotBlank(message = "Avatar không được để trống.")
    String avatar;
}
