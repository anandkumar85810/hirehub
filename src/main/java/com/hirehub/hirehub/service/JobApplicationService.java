package com.hirehub.hirehub.service;

import com.hirehub.hirehub.dto.request.JobApplicationRequest;
import com.hirehub.hirehub.dto.response.JobApplicationResponse;
import com.hirehub.hirehub.entity.Job;
import com.hirehub.hirehub.entity.JobApplication;
import com.hirehub.hirehub.entity.User;
import com.hirehub.hirehub.enums.ApplicationStatus;
import com.hirehub.hirehub.exception.ResourceNotFoundException;
import com.hirehub.hirehub.repository.JobApplicationRepository;
import com.hirehub.hirehub.repository.JobRepository;
import com.hirehub.hirehub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public JobApplicationService(
            JobApplicationRepository jobApplicationRepository,
            UserRepository userRepository,
            JobRepository jobRepository) {

        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }


    // Apply for Job
    public JobApplicationResponse applyForJob(
            JobApplicationRequest jobApplicationRequest) {

        // Find Candidate
        User candidate = userRepository.findById(
                jobApplicationRequest.getCandidateId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "User not found with id: "
                                + jobApplicationRequest.getCandidateId()
                )
        );


        // Find Job
        Job job = jobRepository.findById(
                jobApplicationRequest.getJobId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Job not found with id: "
                                + jobApplicationRequest.getJobId()
                )
        );


        // Check duplicate application
        boolean alreadyApplied =
                jobApplicationRepository.existsByCandidateIdAndJobId(
                        jobApplicationRequest.getCandidateId(),
                        jobApplicationRequest.getJobId()
                );

        if (alreadyApplied) {
            throw new ResourceNotFoundException(
                    "Candidate has already applied for this job"
            );
        }


        // Create Application
        JobApplication jobApplication =
                new JobApplication();

        jobApplication.setJob(job);
        jobApplication.setCandidate(candidate);
        jobApplication.setStatus(ApplicationStatus.APPLIED);
        jobApplication.setAppliedAt(LocalDateTime.now());


        // Save Application
        JobApplication savedJobApplication =
                jobApplicationRepository.save(jobApplication);


        // Create Response
        JobApplicationResponse jobApplicationResponse =
                new JobApplicationResponse();

        jobApplicationResponse.setId(
                savedJobApplication.getId()
        );

        jobApplicationResponse.setJobId(
                savedJobApplication.getJob().getId()
        );

        jobApplicationResponse.setCandidateId(
                savedJobApplication.getCandidate().getId()
        );

        jobApplicationResponse.setCandidateName(
                savedJobApplication.getCandidate().getUsername()
        );

        jobApplicationResponse.setJobTitle(
                savedJobApplication.getJob().getTitle()
        );

        jobApplicationResponse.setStatus(
                savedJobApplication.getStatus()
        );

        jobApplicationResponse.setAppliedAt(
                savedJobApplication.getAppliedAt()
        );

        return jobApplicationResponse;
    }


    // Get Application By ID
    public JobApplicationResponse getApplicationById(Long id) {

        JobApplication jobApplication =
                jobApplicationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Application not found with id: "
                                                + id
                                )
                        );


        JobApplicationResponse jobApplicationResponse =
                new JobApplicationResponse();

        jobApplicationResponse.setId(
                jobApplication.getId()
        );

        jobApplicationResponse.setJobId(
                jobApplication.getJob().getId()
        );

        jobApplicationResponse.setCandidateName(
                jobApplication.getCandidate().getUsername()
        );

        jobApplicationResponse.setCandidateId(
                jobApplication.getCandidate().getId()
        );

        jobApplicationResponse.setJobTitle(
                jobApplication.getJob().getTitle()
        );

        jobApplicationResponse.setStatus(
                jobApplication.getStatus()
        );

        jobApplicationResponse.setAppliedAt(
                jobApplication.getAppliedAt()
        );

        return jobApplicationResponse;
    }


    // Get Applications By Candidate
    public List<JobApplicationResponse> getApplicationByCandidate(
            Long candidateId) {

        return jobApplicationRepository
                .findByCandidateId(candidateId)
                .stream()
                .map(jobApplication -> {

                    JobApplicationResponse jobApplicationResponse =
                            new JobApplicationResponse();

                    jobApplicationResponse.setId(
                            jobApplication.getId()
                    );

                    jobApplicationResponse.setCandidateId(
                            jobApplication.getCandidate().getId()
                    );

                    jobApplicationResponse.setCandidateName(
                            jobApplication.getCandidate().getUsername()
                    );

                    jobApplicationResponse.setJobId(
                            jobApplication.getJob().getId()
                    );

                    jobApplicationResponse.setJobTitle(
                            jobApplication.getJob().getTitle()
                    );

                    jobApplicationResponse.setStatus(
                            jobApplication.getStatus()
                    );

                    jobApplicationResponse.setAppliedAt(
                            jobApplication.getAppliedAt()
                    );

                    return jobApplicationResponse;
                })
                .toList();
    }


    // Get Applications By Job
    public List<JobApplicationResponse> getApplicationByJob(
            Long jobId) {

        return jobApplicationRepository
                .findByJobId(jobId)
                .stream()
                .map(jobApplication -> {

                    JobApplicationResponse jobApplicationResponse =
                            new JobApplicationResponse();

                    jobApplicationResponse.setId(
                            jobApplication.getId()
                    );

                    jobApplicationResponse.setJobId(
                            jobApplication.getJob().getId()
                    );

                    jobApplicationResponse.setCandidateId(
                            jobApplication.getCandidate().getId()
                    );

                    jobApplicationResponse.setCandidateName(
                            jobApplication.getCandidate().getUsername()
                    );

                    jobApplicationResponse.setJobTitle(
                            jobApplication.getJob().getTitle()
                    );

                    jobApplicationResponse.setStatus(
                            jobApplication.getStatus()
                    );

                    jobApplicationResponse.setAppliedAt(
                            jobApplication.getAppliedAt()
                    );

                    return jobApplicationResponse;
                })
                .toList();
    }
}