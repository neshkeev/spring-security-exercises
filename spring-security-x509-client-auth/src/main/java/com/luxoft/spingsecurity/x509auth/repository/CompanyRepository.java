package com.luxoft.spingsecurity.x509auth.repository;

import com.luxoft.spingsecurity.x509auth.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
