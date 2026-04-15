package com.study.khoa_hoc.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    @Column(columnDefinition = "TEXT")
    String description;

    Integer duration;

    Double price;

    // 1-N với Classes
    @OneToMany(mappedBy = "course")
    private List<Classes> classes;
}
