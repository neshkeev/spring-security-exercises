package com.luxoft.spingsecurity.rememberme.repository;

import com.luxoft.spingsecurity.rememberme.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
