package com.study.khoa_hoc.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 50, nullable = false)
    String phone;

    @Column(length = 100, nullable = false)
    String address;

    @Column(nullable = false)
    String avatar;

    @OneToOne
    @JoinColumn(name= "user_id", unique = true)
    User user;

}
