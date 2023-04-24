package com.luxoft.spingsecurity.acl.repository;

import com.luxoft.spingsecurity.acl.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Company> findAll();
}
