package com.company_rest_full.repository;

import com.company_rest_full.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsCompanyByCompanyName(String companyName);
}
