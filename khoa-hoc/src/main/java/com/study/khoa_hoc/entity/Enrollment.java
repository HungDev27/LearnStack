package com.study.khoa_hoc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enrollments")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "enrollment_date")
    private String enrollmentDate;

    @Column(name = "final_score")
    private Integer finalScore;

    // N-1 với Student
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // N-1 với Classes
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes;
}