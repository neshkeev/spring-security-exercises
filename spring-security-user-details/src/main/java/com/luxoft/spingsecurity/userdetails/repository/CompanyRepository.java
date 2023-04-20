package com.luxoft.spingsecurity.userdetails.repository;

import com.luxoft.spingsecurity.userdetails.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
