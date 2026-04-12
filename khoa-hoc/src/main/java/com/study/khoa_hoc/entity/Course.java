package com.study.khoa_hoc.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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
    Double price;

    // [BỔ SUNG]: Soft Delete cho khóa học (ẩn khỏi danh sách bán nhưng vẫn giữ báo cáo)
    boolean deleted = false;

    // Nhiều khóa học có thể được tạo bởi một Giảng viên (User)
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user; // Người dạy

    // Một khóa học có nhiều bài học, xóa khóa học sẽ xóa luôn các bài học liên quan (cascade)
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    List<Lesson> lessons;

    @OneToMany(mappedBy = "course")
    List<Enrollment> enrollments;
//
    @OneToMany(mappedBy = "course")
    List<Review> reviews;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}
