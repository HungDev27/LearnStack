package com.study.khoa_hoc.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer rating; // 1-5 sao
    @Column(columnDefinition = "TEXT")
    String comment;

    @ManyToOne
    @JoinColumn(name = "user_id") // Ai đánh giá?
    User user;

    @ManyToOne
    @JoinColumn(name = "course_id") // Đánh giá khóa học nào?
    Course course;

    @CreationTimestamp
    LocalDateTime created_at;
}

