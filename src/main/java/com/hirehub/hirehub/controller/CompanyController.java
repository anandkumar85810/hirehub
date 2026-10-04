package com.hirehub.hirehub.controller;

import com.hirehub.hirehub.dto.request.CompanyRequest;
import com.hirehub.hirehub.dto.response.CompanyResponse;
import com.hirehub.hirehub.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody CompanyRequest companyRequest) {
        CompanyResponse companyResponse = companyService.createCompany(companyRequest);

        return ResponseEntity.ok(companyResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        CompanyResponse companyResponse = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyResponse);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        List<CompanyResponse> companyResponse = companyService.getAllCompanies();
        return ResponseEntity.ok(companyResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@Valid @RequestBody CompanyRequest companyRequest, @PathVariable Long id) {
        CompanyResponse companyResponse = companyService.updateCompany(companyRequest, id);

        return ResponseEntity.ok(companyResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);

        return ResponseEntity.ok("Company deleted");
    }
}
