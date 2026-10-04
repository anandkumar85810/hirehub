package com.hirehub.hirehub.service;

import com.hirehub.hirehub.dto.request.CandidateProfileRequest;
import com.hirehub.hirehub.dto.response.CandidateProfileResponse;
import com.hirehub.hirehub.entity.CandidateProfile;
import com.hirehub.hirehub.entity.User;
import com.hirehub.hirehub.exception.ResourceNotFoundException;
import com.hirehub.hirehub.repository.CandidateProfileRepository;
import com.hirehub.hirehub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateProfileService {

    private final CandidateProfileRepository candidateProfileRepository;
    private final UserRepository userRepository;

    public CandidateProfileService(CandidateProfileRepository candidateProfileRepository, UserRepository userRepository) {
        this.candidateProfileRepository = candidateProfileRepository;
        this.userRepository = userRepository;
    }

    public CandidateProfileResponse createCandidateProfile(CandidateProfileRequest candidateProfileRequest) {

        User user = userRepository.findById(candidateProfileRequest.getUserId()).orElseThrow(() ->
                new ResourceNotFoundException("User with id " + candidateProfileRequest.getUserId() + " not found"));

        CandidateProfile candidateProfile = new CandidateProfile();

        candidateProfile.setUser(user);
        candidateProfile.setProfilePhoto(candidateProfileRequest.getProfilePhoto());
        candidateProfile.setEducation(candidateProfileRequest.getEducation());
        candidateProfile.setExperience(candidateProfileRequest.getExperience());
        candidateProfile.setSkills(candidateProfileRequest.getSkills());
        candidateProfile.setExpectedSalary(candidateProfileRequest.getExpectedSalary());
        candidateProfile.setBio(candidateProfileRequest.getBio());
        candidateProfile.setLocation(candidateProfileRequest.getLocation());

        CandidateProfile savedCandidateProfile = candidateProfileRepository.save(candidateProfile);

        CandidateProfileResponse candidateProfileResponse = new CandidateProfileResponse();

        candidateProfileResponse.setId(savedCandidateProfile.getId());
        candidateProfileResponse.setUserId(savedCandidateProfile.getUser().getId());
        candidateProfileResponse.setProfilePhoto(savedCandidateProfile.getProfilePhoto());
        candidateProfileResponse.setEducation(savedCandidateProfile.getEducation());
        candidateProfileResponse.setExpectedSalary(savedCandidateProfile.getExpectedSalary());
        candidateProfileResponse.setSkills(savedCandidateProfile.getSkills());
        candidateProfileResponse.setBio(savedCandidateProfile.getBio());
        candidateProfileResponse.setLocation(savedCandidateProfile.getLocation());

        return candidateProfileResponse;
    }

    public CandidateProfileResponse findById(Long id) {
        CandidateProfile candidateProfile = candidateProfileRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Candidate Profile with id " + id + " not found"));

        CandidateProfileResponse candidateProfileResponse = new CandidateProfileResponse();

        candidateProfileResponse.setId(candidateProfile.getId());
        candidateProfileResponse.setUserId(candidateProfile.getUser().getId());
        candidateProfileResponse.setProfilePhoto(candidateProfile.getProfilePhoto());
        candidateProfileResponse.setEducation(candidateProfile.getEducation());
        candidateProfileResponse.setSkills(candidateProfile.getSkills());
        candidateProfileResponse.setExperience(candidateProfile.getExperience());
        candidateProfileResponse.setExpectedSalary(candidateProfile.getExpectedSalary());
        candidateProfileResponse.setBio(candidateProfile.getBio());
        candidateProfileResponse.setLocation(candidateProfile.getLocation());

        return candidateProfileResponse;
    }

    public List<CandidateProfileResponse> findAll() {
        List<CandidateProfile> candidateProfiles = candidateProfileRepository.findAll();

        List<CandidateProfileResponse> candidateProfileResponseList = new ArrayList<>();

        for(CandidateProfile candidateProfile : candidateProfiles){

            CandidateProfileResponse candidateProfileResponse = new CandidateProfileResponse();

            candidateProfileResponse.setId(candidateProfile.getId());
            candidateProfileResponse.setUserId(candidateProfile.getUser().getId());
            candidateProfileResponse.setProfilePhoto(candidateProfile.getProfilePhoto());
            candidateProfileResponse.setEducation(candidateProfile.getEducation());
            candidateProfileResponse.setSkills(candidateProfile.getSkills());
            candidateProfileResponse.setExperience(candidateProfile.getExperience());
            candidateProfileResponse.setExpectedSalary(candidateProfile.getExpectedSalary());
            candidateProfileResponse.setBio(candidateProfile.getBio());
            candidateProfileResponse.setLocation(candidateProfile.getLocation());

            candidateProfileResponseList.add(candidateProfileResponse);
        }
        return candidateProfileResponseList;
    }

    public CandidateProfileResponse updateCandidateProfile(CandidateProfileRequest candidateProfileRequest, Long id) {

        CandidateProfile candidateProfile = candidateProfileRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Candidate Profile with id " + id + " not found"));

        candidateProfile.setProfilePhoto(candidateProfileRequest.getProfilePhoto());
        candidateProfile.setEducation(candidateProfileRequest.getEducation());
        candidateProfile.setExperience(candidateProfileRequest.getExperience());
        candidateProfile.setSkills(candidateProfileRequest.getSkills());
        candidateProfile.setExpectedSalary(candidateProfileRequest.getExpectedSalary());
        candidateProfile.setBio(candidateProfileRequest.getBio());
        candidateProfile.setLocation(candidateProfileRequest.getLocation());

        CandidateProfile savedCandidateProfile = candidateProfileRepository.save(candidateProfile);

        CandidateProfileResponse candidateProfileResponse = new CandidateProfileResponse();

        candidateProfileResponse.setId(savedCandidateProfile.getId());
        candidateProfileResponse.setUserId(savedCandidateProfile.getUser().getId());
        candidateProfileResponse.setProfilePhoto(savedCandidateProfile.getProfilePhoto());
        candidateProfileResponse.setEducation(savedCandidateProfile.getEducation());
        candidateProfileResponse.setExperience(savedCandidateProfile.getExperience());
        candidateProfileResponse.setSkills(savedCandidateProfile.getSkills());
        candidateProfileResponse.setExpectedSalary(savedCandidateProfile.getExpectedSalary());
        candidateProfileResponse.setBio(savedCandidateProfile.getBio());
        candidateProfileResponse.setLocation(savedCandidateProfile.getLocation());

        return candidateProfileResponse;
    }

    public void deleteCandidateProfile(Long id) {
        CandidateProfile candidateProfile = candidateProfileRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Candidate Profile with id " + id + " not found"));

        candidateProfileRepository.delete(candidateProfile);
    }
}
