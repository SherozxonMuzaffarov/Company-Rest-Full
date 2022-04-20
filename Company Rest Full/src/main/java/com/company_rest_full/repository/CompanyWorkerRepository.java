package com.company_rest_full.repository;

import com.company_rest_full.entity.CompanyWorker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyWorkerRepository extends JpaRepository<CompanyWorker, Long> {

    boolean existsByPhoneNumber(String phoneNumber);
}
