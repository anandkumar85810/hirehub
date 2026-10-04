package com.hirehub.hirehub.controller;

import com.hirehub.hirehub.dto.request.CandidateProfileRequest;
import com.hirehub.hirehub.dto.response.CandidateProfileResponse;
import com.hirehub.hirehub.service.CandidateProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateProfileController {

    private final CandidateProfileService candidateProfileService;

    public CandidateProfileController(CandidateProfileService candidateProfileService) {
        this.candidateProfileService = candidateProfileService;
    }

    @PostMapping
    public ResponseEntity<CandidateProfileResponse> createCandidateProfile(@Valid @RequestBody CandidateProfileRequest candidateProfileRequest) {
        CandidateProfileResponse candidateProfileResponse = candidateProfileService.createCandidateProfile(candidateProfileRequest);
        return ResponseEntity.ok().body(candidateProfileResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateProfileResponse> getCandidateProfileById(@PathVariable Long id) {
        CandidateProfileResponse candidateProfileResponse = candidateProfileService.findById(id);
        return ResponseEntity.ok().body(candidateProfileResponse);
    }

    @GetMapping
    public ResponseEntity<List<CandidateProfileResponse>> getAllCandidateProfile() {
        List<CandidateProfileResponse> candidateProfileResponse = candidateProfileService.findAll();
        return ResponseEntity.ok().body(candidateProfileResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateProfileResponse> updateCandidateProfile(@Valid @RequestBody CandidateProfileRequest candidateProfileRequest, @PathVariable Long id) {
        CandidateProfileResponse candidateProfileResponse = candidateProfileService.updateCandidateProfile(candidateProfileRequest, id);

        return ResponseEntity.ok().body(candidateProfileResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidateProfile(@PathVariable Long id) {
        candidateProfileService.deleteCandidateProfile(id);
        return ResponseEntity.ok("Candidate Profile deleted Successfully");
    }
}
