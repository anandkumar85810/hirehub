package com.hirehub.hirehub.controller;

import com.hirehub.hirehub.dto.request.JobRequest;
import com.hirehub.hirehub.dto.response.JobResponse;
import com.hirehub.hirehub.service.CompanyService;
import com.hirehub.hirehub.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobResponse> createJobs(@Valid @RequestBody JobRequest jobRequest) {
        JobResponse jobResponse = jobService.createJob(jobRequest);
        return ResponseEntity.ok(jobResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long id) {
        JobResponse jobResponse = jobService.getJobById(id);
        return ResponseEntity.ok(jobResponse);
    }

    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        List<JobResponse> jobResponses = jobService.getAllJob();
        return ResponseEntity.ok(jobResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJobById(@Valid @RequestBody JobRequest jobRequest, @PathVariable Long id) {
        JobResponse jobResponse = jobService.updateJob(jobRequest, id);

        return ResponseEntity.ok(jobResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        jobService.deleteJobById(id);

        return ResponseEntity.ok("Job has been deleted");
    }
}
