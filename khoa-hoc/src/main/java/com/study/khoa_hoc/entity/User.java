package com.study.khoa_hoc.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
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


    // Quan hệ 1-1 với Profile, mappedBy trỏ tới biến "user" trong class Profile
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Profile profile;

    // 1-N với Enrollment
    @OneToMany(mappedBy = "user")
    private List<Enrollment> enrollments;

}
