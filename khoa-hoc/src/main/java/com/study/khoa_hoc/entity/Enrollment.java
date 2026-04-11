package com.study.khoa_hoc.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // [BỔ SUNG]: Trạng thái thanh toán (VD: PAID, UNPAID, REFUNDED)
    String payment_status;

    // [BỔ SUNG]: Số tiền thực tế học viên đã trả (phòng trường hợp có giảm giá)
    Double amount_paid;
    @ManyToOne
    @JoinColumn(name = "user_id") // Ai đăng ký?
    User user;

    @ManyToOne
    @JoinColumn(name = "course_id") // Đăng ký khóa học nào?
    Course course;

    String status; // Nên dùng Enum: PENDING, COMPLETED, CANCELLED

    @CreationTimestamp
    LocalDateTime created_at;
}