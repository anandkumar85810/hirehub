package com.hirehub.hirehub.controller;

import com.hirehub.hirehub.dto.request.RecruiterProfileRequest;
import com.hirehub.hirehub.dto.response.RecruiterProfileResponse;
import com.hirehub.hirehub.service.RecruiterProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruiterProfiles")
public class RecruiterProfileController {

    private final RecruiterProfileService recruiterProfileService;

    public RecruiterProfileController(RecruiterProfileService recruiterProfileService) {
        this.recruiterProfileService = recruiterProfileService;
    }

    @PostMapping
    public ResponseEntity<RecruiterProfileResponse> createRecruiterProfile(@Valid @RequestBody RecruiterProfileRequest recruiterProfileRequest) {
        RecruiterProfileResponse recruiterProfileResponse = recruiterProfileService.createRecruiterProfile(recruiterProfileRequest);

        return new ResponseEntity<>(recruiterProfileResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruiterProfileResponse> getRecruiterProfileById(@PathVariable Long id) {
        RecruiterProfileResponse recruiterProfileResponse = recruiterProfileService.getRecruiterProfileById(id);

        return new ResponseEntity<>(recruiterProfileResponse, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<RecruiterProfileResponse> getRecruiterProfileByUserId(@PathVariable Long userId) {
        RecruiterProfileResponse recruiterProfileResponse = recruiterProfileService.getRecruiterProfileByUserId(userId);

        return new ResponseEntity<>(recruiterProfileResponse, HttpStatus.OK);
    }
}
