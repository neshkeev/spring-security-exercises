package com.luxoft.spingsecurity.userdetails.dto.converters;

import com.luxoft.spingsecurity.userdetails.dto.CompanyDto;
import com.luxoft.spingsecurity.userdetails.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyDtoConverter {

    public CompanyDto toDto(Company domain) {
        return new CompanyDto(domain.getId(), domain.getName());
    }

    public Company toDomain(CompanyDto dto) {
        var domain = new Company();
        domain.setId(dto.getId());
        domain.setName(dto.getName());
        return domain;
    }

    public Company toDomain(CompanyDto dto, Company original) {
        original.setName(dto.getName());
        return original;
    }
}
