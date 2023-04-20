package com.luxoft.spingsecurity.basics.repository;

import com.luxoft.spingsecurity.basics.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
