package com.hirehub.hirehub.service;

import com.hirehub.hirehub.dto.request.CompanyRequest;
import com.hirehub.hirehub.dto.response.CompanyResponse;
import com.hirehub.hirehub.entity.Company;
import com.hirehub.hirehub.exception.ResourceNotFoundException;
import com.hirehub.hirehub.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class    CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyResponse createCompany(CompanyRequest companyRequest) {

        Company company = new Company();

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

            companyResponseList.add(companyResponse);
        }
        return companyResponseList;
    }

    public CompanyResponse updateCompany(CompanyRequest companyRequest, Long id) {

        Company company = companyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Company Not Found with: " +id));

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

        return companyResponse;
    }

    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Company Not Found with: " +id));
        companyRepository.delete(company);
    }

}
