package com.study.khoa_hoc.repository;

import com.study.khoa_hoc.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByEmail(String email);

    boolean existsBySdt(String sdt);
}
