package com.hirehub.hirehub.service;

import com.hirehub.hirehub.dto.request.RecruiterProfileRequest;
import com.hirehub.hirehub.dto.response.RecruiterProfileResponse;
import com.hirehub.hirehub.entity.Company;
import com.hirehub.hirehub.entity.RecruiterProfile;
import com.hirehub.hirehub.entity.User;
import com.hirehub.hirehub.exception.ResourceNotFoundException;
import com.hirehub.hirehub.repository.CompanyRepository;
import com.hirehub.hirehub.repository.RecruiterProfileRepository;
import com.hirehub.hirehub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository, UserRepository userRepository, CompanyRepository companyRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public RecruiterProfileResponse createRecruiterProfile(RecruiterProfileRequest recruiterProfileRequest) {

        User user = userRepository.findById(recruiterProfileRequest.getUserId()).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id " + recruiterProfileRequest.getUserId()));

        Company company = companyRepository.findById(recruiterProfileRequest.getCompanyId()).orElseThrow(() ->
                new  ResourceNotFoundException("Company not found with id " + recruiterProfileRequest.getCompanyId()));

        // Check if recruiter profile already exists
        if (recruiterProfileRepository.existsByUserId(user.getId())) {
            throw new ResourceNotFoundException("User with id " + user.getId() + " already exists");
        }

        //Create Recruiter profile
        RecruiterProfile recruiterProfile = new RecruiterProfile();

        recruiterProfile.setUser(user);
        recruiterProfile.setCompany(company);
        recruiterProfile.setDesignation(recruiterProfileRequest.getDesignation());
        recruiterProfile.setDepartment(recruiterProfileRequest.getDepartment());
        recruiterProfile.setPhone(recruiterProfileRequest.getPhone());
        recruiterProfile.setBio(recruiterProfileRequest.getBio());

        RecruiterProfile savedRecruiterProfile = recruiterProfileRepository.save(recruiterProfile);

        RecruiterProfileResponse recruiterProfileResponse = new RecruiterProfileResponse();

        recruiterProfileResponse.setId(savedRecruiterProfile.getId());
        recruiterProfileResponse.setUserId(user.getId());
        recruiterProfileResponse.setCompanyId(company.getId());
        recruiterProfileResponse.setDesignation(savedRecruiterProfile.getDesignation());
        recruiterProfileResponse.setDepartment(savedRecruiterProfile.getDepartment());
        recruiterProfileResponse.setPhone(savedRecruiterProfile.getPhone());
        recruiterProfileResponse.setBio(savedRecruiterProfile.getBio());

        return recruiterProfileResponse;
    }

    public RecruiterProfileResponse getRecruiterProfileById(Long recruiterProfileId) {
       RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(recruiterProfileId).orElseThrow(() ->
               new ResourceNotFoundException("RecruiterProfile with id " + recruiterProfileId + " not found"));

       RecruiterProfileResponse recruiterProfileResponse = new RecruiterProfileResponse();

       recruiterProfileResponse.setId(recruiterProfile.getId());
       recruiterProfileResponse.setUserId(recruiterProfile.getUser().getId());
       recruiterProfileResponse.setCompanyId(recruiterProfile.getCompany().getId());
       recruiterProfileResponse.setDesignation(recruiterProfile.getDesignation());
       recruiterProfileResponse.setDepartment(recruiterProfile.getDepartment());
       recruiterProfileResponse.setPhone(recruiterProfile.getPhone());
       recruiterProfileResponse.setBio(recruiterProfile.getBio());

       return recruiterProfileResponse;
    }

    public RecruiterProfileResponse getRecruiterProfileByUserId(Long userId) {
        RecruiterProfile recruiterProfile = recruiterProfileRepository.findByUserId(userId).orElseThrow(() ->
                new ResourceNotFoundException("RecruiterProfile with id " + userId + " not found"));

        RecruiterProfileResponse recruiterProfileResponse = new RecruiterProfileResponse();

        recruiterProfileResponse.setId(recruiterProfile.getId());
        recruiterProfileResponse.setUserId(recruiterProfile.getUser().getId());
        recruiterProfileResponse.setCompanyId(recruiterProfile.getCompany().getId());
        recruiterProfileResponse.setDesignation(recruiterProfile.getDesignation());
        recruiterProfileResponse.setDepartment(recruiterProfile.getDepartment());
        recruiterProfileResponse.setPhone(recruiterProfile.getPhone());
        recruiterProfileResponse.setBio(recruiterProfile.getBio());

        return recruiterProfileResponse;
    }
}
