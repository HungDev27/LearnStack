package com.study.khoa_hoc.dto.response;

import com.study.khoa_hoc.entity.Profile;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String name;
    String email;
    ProfileResponse profileResponse;
}
