package com.study.khoa_hoc.repository;

import com.study.khoa_hoc.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    // Trả về cái hộp chứa Profile
    Optional<Profile> findByUserId(Long userId);
}
