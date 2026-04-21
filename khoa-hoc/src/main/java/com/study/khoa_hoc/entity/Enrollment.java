package com.study.khoa_hoc.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreationTimestamp
    @Column(name = "enrollment_date", updatable = false)
    private LocalDate enrollmentDate;

    @Column(name = "final_score")
    private Integer finalScore;

    //private Integer quantity;

    // N-1 với Student
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // N-1 với Classes
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes;
}