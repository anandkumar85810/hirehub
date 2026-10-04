package com.hirehub.hirehub.repository;

import com.hirehub.hirehub.entity.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile, Long> {
    Optional<RecruiterProfile> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
    boolean existsByUserIdAndCompanyId(Long userId, Long companyId);
}
