package com.study.khoa_hoc.repository;

import com.study.khoa_hoc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.profile")
    List<User> findAllWithProfile();

    // Thêm method tùy chỉnh nếu cần (ví dụ: tìm theo email)
   // Optional<User> findByEmail(String email);  // Tìm User theo email (unique field)
}
      