package com.luxoft.spingsecurity.anonymousauth.repository;

import com.luxoft.spingsecurity.anonymousauth.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
