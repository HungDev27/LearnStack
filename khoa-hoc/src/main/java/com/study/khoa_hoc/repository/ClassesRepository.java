package com.study.khoa_hoc.repository;

import com.study.khoa_hoc.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClassesRepository extends JpaRepository<Classes, Long> {
    @Query("SELECT c FROM Classes c JOIN FETCH c.teacher JOIN FETCH c.course")
    List<Classes> findAllWithRelation();

    @Query("""
            SELECT c FROM Classes c 
            LEFT JOIN FETCH c.teacher 
            LEFT JOIN FETCH c.course
            WHERE c.id = :id
            """)
    Optional<Classes> findByIdWithRelation(Long id);
}
