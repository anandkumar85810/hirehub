package com.hirehub.hirehub.repository;

import com.hirehub.hirehub.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
