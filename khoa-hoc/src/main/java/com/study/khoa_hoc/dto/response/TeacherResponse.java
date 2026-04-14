package com.study.khoa_hoc.dto.response;

import com.study.khoa_hoc.entity.Class;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherResponse {

    Integer id;

    String name;

    String email;

}
