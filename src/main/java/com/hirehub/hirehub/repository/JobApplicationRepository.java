package com.hirehub.hirehub.repository;

import com.hirehub.hirehub.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByCandidateId(Long candidateId);
    List<JobApplication> findByJobId(Long JobId);
    Boolean existsByCandidateIdAndJobId(Long candidateId, Long jobId);
}
