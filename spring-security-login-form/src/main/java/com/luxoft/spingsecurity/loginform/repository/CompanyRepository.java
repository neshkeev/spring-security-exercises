package com.luxoft.spingsecurity.loginform.repository;

import com.luxoft.spingsecurity.loginform.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
