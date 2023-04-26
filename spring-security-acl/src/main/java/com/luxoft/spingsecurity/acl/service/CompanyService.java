package com.luxoft.spingsecurity.acl.service;

import com.luxoft.spingsecurity.acl.dto.CompanyDto;
import com.luxoft.spingsecurity.acl.dto.OrderDto;
import com.luxoft.spingsecurity.acl.dto.converters.CompanyDtoConverter;
import com.luxoft.spingsecurity.acl.dto.converters.OrderDtoConverter;
import com.luxoft.spingsecurity.acl.repository.CompanyRepository;
import com.luxoft.spingsecurity.acl.repository.OrderRepository;
import com.luxoft.spingsecurity.acl.repository.UserRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    private final CompanyDtoConverter companyDtoConverter;
    private final OrderDtoConverter orderDtoConverter;

    public CompanyService(CompanyRepository companyRepository,
                          UserRepository userRepository,
                          OrderRepository orderRepository,
                          CompanyDtoConverter companyDtoConverter,
                          OrderDtoConverter orderDtoConverter) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.companyDtoConverter = companyDtoConverter;
        this.orderDtoConverter = orderDtoConverter;
    }

    @PostFilter(
            value = "hasPermission(filterObject.id, 'Company', 'read')"
    )
    @Transactional(readOnly = true)
    public List<CompanyDto> getAll() {
        return companyRepository.findAll().stream()
            .map(companyDtoConverter::toDto)
            .collect(toList());
    }

    @Secured("ROLE_ADMIN")
    @Transactional(readOnly = true)
    public CompanyDto getById(long companyId) {
        return companyRepository.findById(companyId)
            .map(companyDtoConverter::toDto)
            .orElseThrow(() -> new IllegalArgumentException("Company does not exist"));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    @Transactional
    public CompanyDto createCompany(CompanyDto newCompany, long userId) {
        var user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        var company = companyDtoConverter.toDomain(newCompany);
        var withId = companyRepository.save(company);
        user.getCompanies().add(company);
        userRepository.save(user);
        // TODO: create ACL and store it
        return companyDtoConverter.toDto(withId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @Transactional
    public CompanyDto updateCompany(CompanyDto companyDto) {
        var company = companyRepository.findById(companyDto.getId())
            .orElseThrow(() -> new IllegalArgumentException("Company does not exist"));
        var updated = companyDtoConverter.toDomain(companyDto, company);
        var fromDb = companyRepository.save(updated);
        return companyDtoConverter.toDto(fromDb);
    }

    // Just example of getting object without entity
    @PreFilter(
        value = "hasPermission(filterObject.id, 'Company', 'read')",
        filterTarget = "companies"
    )
    @Transactional
    public List<CompanyDto> updateCompany(List<CompanyDto> companies) {
        return companies.stream()
            .map(companyDto -> {
                var company = companyRepository.findById(companyDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Company does not exist"));
                var updated = companyDtoConverter.toDomain(companyDto, company);
                var fromDb = companyRepository.save(updated);
                return companyDtoConverter.toDto(fromDb);
            })
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getCompanyOrders(long companyId) {
        var company = companyRepository.findById(companyId)
            .orElseThrow(() -> new IllegalArgumentException("Company does not exist"));
        return company.getOrders().stream()
            .map(orderDtoConverter::toDto)
            .collect(toList());
    }

    @Transactional
    public OrderDto createOrder(long companyId, OrderDto orderDto) {
        var company = companyRepository.findById(companyId)
            .orElseThrow(() -> new IllegalArgumentException("Company does not exist"));
        var order = orderDtoConverter.toDomain(orderDto, company);
        var withId = orderRepository.save(order);
        return orderDtoConverter.toDto(withId);
    }

    @Transactional
    public void deleteOrder(long orderId) {
        // Yes, companyId is not used now
        orderRepository.deleteById(orderId);
    }
}
