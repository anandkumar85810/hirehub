package com.hirehub.hirehub.controller;

import com.hirehub.hirehub.dto.request.JobApplicationRequest;
import com.hirehub.hirehub.dto.response.JobApplicationResponse;
import com.hirehub.hirehub.entity.JobApplication;
import com.hirehub.hirehub.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponse> applyForJob(@Valid @RequestBody JobApplicationRequest jobApplicationRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(jobApplicationService.applyForJob(jobApplicationRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> getApplicationById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(jobApplicationService.getApplicationById(id));
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<JobApplicationResponse>> getApplicationsByCandidate(@PathVariable Long candidateId) {
        return ResponseEntity.ok(jobApplicationService.getApplicationByCandidate(candidateId));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplicationResponse>> getApplicationsByJob(@PathVariable Long candidateId) {
        return ResponseEntity.ok(jobApplicationService.getApplicationByJob(candidateId));
    }
}
