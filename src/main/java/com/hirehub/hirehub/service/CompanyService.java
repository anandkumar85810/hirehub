package com.hirehub.hirehub.service;

import com.hirehub.hirehub.dto.request.CompanyRequest;
import com.hirehub.hirehub.dto.response.CompanyResponse;
import com.hirehub.hirehub.entity.Company;
import com.hirehub.hirehub.entity.User;
import com.hirehub.hirehub.enums.Role;
import com.hirehub.hirehub.exception.ResourceNotFoundException;
import com.hirehub.hirehub.repository.CompanyRepository;
import com.hirehub.hirehub.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
public class    CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    private User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new AccessDeniedException("Please login first");
        }

        return userRepository.findByEmail(authentication.getName()).orElseThrow(() ->
                new ResourceNotFoundException("Logged in User not found"));
    }

    private void checkOwnerAdmin(Company company, User currentUser) {
        boolean isAdmin = currentUser.getRole().equals(Role.ADMIN);
        boolean isOwner = company.getUser() != null && company.getUser().getId().equals(currentUser.getId());
        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException("You are not allowed to perform this action");
        }
    }

    public CompanyResponse createCompany(CompanyRequest companyRequest) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        String email = authentication.getName();
//
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Logged-in user not found"));

        User user = getLoggedInUser();

        if (user.getRole() != Role.COMPANY) {
            throw new AccessDeniedException("Only Company users can create a company profiel");
        }

        if (user.getCompany() != null) {
            throw new AccessDeniedException("This user already has a company profile");
        }

        Company company = new Company();

        company.setUser(user);
        user.setCompany(company);
        company.setName(companyRequest.getName());
        company.setEmail(companyRequest.getEmail());
        company.setPhone(companyRequest.getPhone());
        company.setLogo(companyRequest.getLogo());
        company.setIndustry(companyRequest.getIndustry());
        company.setDescription(companyRequest.getDescription());
        company.setWebsite(companyRequest.getWebsite());
        company.setLocation(companyRequest.getLocation());

        Company savedCompany=companyRepository.save(company);

        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setId(savedCompany.getId());
        companyResponse.setName(savedCompany.getName());
        companyResponse.setEmail(savedCompany.getEmail());
        companyResponse.setPhone(savedCompany.getPhone());
        companyResponse.setLogo(savedCompany.getLogo());
        companyResponse.setIndustry(savedCompany.getIndustry());
        companyResponse.setDescription(savedCompany.getDescription());
        companyResponse.setWebsite(savedCompany.getWebsite());
        companyResponse.setLocation(savedCompany.getLocation());
        if (savedCompany.getUser() != null) {
            companyResponse.setUserId(savedCompany.getUser().getId());
        }

        return companyResponse;
    }

    public CompanyResponse getCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Company Not Found with: " +id));

        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setId(company.getId());
        companyResponse.setName(company.getName());
        companyResponse.setEmail(company.getEmail());
        companyResponse.setPhone(company.getPhone());
        companyResponse.setLogo(company.getLogo());
        companyResponse.setIndustry(company.getIndustry());
        companyResponse.setDescription(company.getDescription());
        companyResponse.setWebsite(company.getWebsite());
        companyResponse.setLocation(company.getLocation());
        if (company.getUser() != null) {
            companyResponse.setUserId(company.getUser().getId());
        }

        return companyResponse;
    }

    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();

        List<CompanyResponse> companyResponseList = new ArrayList<>();
        for (Company company : companies) {
            CompanyResponse companyResponse = new CompanyResponse();

            companyResponse.setId(company.getId());
            companyResponse.setName(company.getName());
            companyResponse.setEmail(company.getEmail());
            companyResponse.setPhone(company.getPhone());
            companyResponse.setLogo(company.getLogo());
            companyResponse.setIndustry(company.getIndustry());
            companyResponse.setDescription(company.getDescription());
            companyResponse.setWebsite(company.getWebsite());
            companyResponse.setLocation(company.getLocation());
            if (company.getUser() != null) {
                companyResponse.setUserId(company.getUser().getId());
            }

            companyResponseList.add(companyResponse);
        }
        return companyResponseList;
    }

    public CompanyResponse updateCompany(CompanyRequest companyRequest, Long id) {

        Company company = companyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Company Not Found with: " +id));

        User currentUser = getLoggedInUser();
        checkOwnerAdmin(company, currentUser);

        company.setName(companyRequest.getName());
        company.setEmail(companyRequest.getEmail());
        company.setPhone(companyRequest.getPhone());
        company.setLogo(companyRequest.getLogo());
        company.setIndustry(companyRequest.getIndustry());
        company.setDescription(companyRequest.getDescription());
        company.setWebsite(companyRequest.getWebsite());
        company.setLocation(companyRequest.getLocation());

        Company savedCompany=companyRepository.save(company);

        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setId(savedCompany.getId());
        companyResponse.setName(savedCompany.getName());
        companyResponse.setEmail(savedCompany.getEmail());
        companyResponse.setPhone(savedCompany.getPhone());
        companyResponse.setLogo(savedCompany.getLogo());
        companyResponse.setIndustry(savedCompany.getIndustry());
        companyResponse.setDescription(savedCompany.getDescription());
        companyResponse.setWebsite(savedCompany.getWebsite());
        companyResponse.setLocation(savedCompany.getLocation());
        if (savedCompany.getUser() != null) {
            companyResponse.setUserId(savedCompany.getUser().getId());
        }

        return companyResponse;
    }

    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Company Not Found with: " +id));

        // Verify logged-in user and ownership
        User currentUser = getLoggedInUser();
        checkOwnerAdmin(company, currentUser);

        companyRepository.delete(company);
    }
}
