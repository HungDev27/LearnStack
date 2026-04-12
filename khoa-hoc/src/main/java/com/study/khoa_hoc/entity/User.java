package com.study.khoa_hoc.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 50, nullable = false)
    String name;

    @Column(length = 100, unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    // [BỔ SUNG]: Phân quyền người dùng (ADMIN, INSTRUCTOR, STUDENT)
    String role;

    // [BỔ SUNG]: Soft Delete - true là đang hoạt động, false là đã bị khóa/xóa
    boolean active = true;

    // Quan hệ 1-1 với Profile, mappedBy trỏ tới biến "user" trong class Profile
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Profile profile;

    // Danh sách các khóa học mà User này tạo ra (Vai trò Giảng viên)
    @OneToMany(mappedBy = "user")
    List<Course> courses;

    // Danh sách các lượt đăng ký khóa học (Vai trò Học viên)
    @OneToMany(mappedBy = "user")
    List<Enrollment> enrollments;

    @OneToMany(mappedBy = "user")
    List<Review> reviews;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime created_at;

    @UpdateTimestamp
    LocalDateTime updated_at;

}
