package com.hirehub.hirehub.repository;

import com.hirehub.hirehub.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
}
