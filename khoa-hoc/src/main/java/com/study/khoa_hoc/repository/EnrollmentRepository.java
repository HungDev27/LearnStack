package com.study.khoa_hoc.repository;

import com.study.khoa_hoc.entity.Enrollment;
import com.study.khoa_hoc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByUser_IdAndClasses_Id(Long userId, Long classesId);

    List<Enrollment> findByUser_Id(Long userId);

    List<Enrollment> findByClasses_Id(Long classesId);
}
