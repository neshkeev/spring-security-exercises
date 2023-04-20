package com.luxoft.spingsecurity.basicauth.repository;

import com.luxoft.spingsecurity.basicauth.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
