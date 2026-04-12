package com.study.khoa_hoc.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "lessons")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    String video_url; // Link video (Youtube, S3...)
    Integer duration; // Tính bằng giây hoặc phút

    // [BỔ SUNG]: Thứ tự bài học trong khóa học (1, 2, 3...)
    @Column(name = "order_index")
    Integer order_index;

    // Nhiều bài học thuộc về một khóa học
    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;
}
