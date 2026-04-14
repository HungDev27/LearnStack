package com.study.khoa_hoc.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    String sdt;

    @Column(unique = true)
    String email;

    // 1-N với Class
    @OneToMany(mappedBy = "teacher")
    List<Class> classes;

}
