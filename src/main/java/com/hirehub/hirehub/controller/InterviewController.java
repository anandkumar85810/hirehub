package com.hirehub.hirehub.controller;

import com.hirehub.hirehub.dto.request.InterviewRequest;
import com.hirehub.hirehub.dto.response.InterviewResponse;
import com.hirehub.hirehub.repository.InterviewRepository;
import com.hirehub.hirehub.service.InterviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping
    public ResponseEntity<InterviewResponse> createInterview(@Valid @RequestBody InterviewRequest interviewRequest) {
        InterviewResponse interviewResponse = interviewService.createInterview(interviewRequest);
        return ResponseEntity.ok(interviewResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewResponse> getInterviewById(@Valid @PathVariable Long id) {
        InterviewResponse interviewResponse = interviewService.getInterviewById(id);
        return ResponseEntity.ok(interviewResponse);
    }

    @GetMapping
    public ResponseEntity<List<InterviewResponse>> getAllInterviews() {
        List<InterviewResponse> interviewResponseList = interviewService.getAllInterviews();
        return ResponseEntity.ok(interviewResponseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterviewResponse> updateInterview(@Valid @RequestBody InterviewRequest interviewRequest, @PathVariable Long id) {
        InterviewResponse interviewResponse = interviewService.updateInterview(interviewRequest, id);
        return ResponseEntity.ok(interviewResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInterviewById(@PathVariable Long id) {
        interviewService.deleteInterview(id);
        return ResponseEntity.ok("Interview has been deleted");
    }
}
