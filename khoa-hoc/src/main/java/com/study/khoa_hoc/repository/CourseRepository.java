package com.study.khoa_hoc.repository;

import com.study.khoa_hoc.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
