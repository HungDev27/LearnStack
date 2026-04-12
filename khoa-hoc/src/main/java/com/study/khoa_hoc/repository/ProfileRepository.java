package com.study.khoa_hoc.repository;

import com.study.khoa_hoc.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
