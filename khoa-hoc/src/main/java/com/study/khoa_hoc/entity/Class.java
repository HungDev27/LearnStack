package com.study.khoa_hoc.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "classes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    // N-1 với Course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // N-1 với Teacher
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    // 1-N với Enrollment
    @OneToMany(mappedBy = "classes")
    private List<Enrollment> enrollments;
}