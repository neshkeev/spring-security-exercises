package com.luxoft.spingsecurity.basics.service;

import com.luxoft.spingsecurity.basics.dto.CompanyDto;
import com.luxoft.spingsecurity.basics.dto.OrderDto;
import com.luxoft.spingsecurity.basics.dto.converters.CompanyDtoConverter;
import com.luxoft.spingsecurity.basics.dto.converters.OrderDtoConverter;
import com.luxoft.spingsecurity.basics.repository.CompanyRepository;
import com.luxoft.spingsecurity.basics.repository.OrderRepository;
import com.luxoft.spingsecurity.basics.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<CompanyDto> getAll() {
        return companyRepository.findAll().stream()
            .map(companyDtoConverter::toDto)
            .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<CompanyDto> getAllByUserId(long userId) {
        var user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        return user.getCompanies().stream()
            .map(companyDtoConverter::toDto)
            .collect(toList());
    }

    @Transactional(readOnly = true)
    public CompanyDto getById(long companyId) {
        return companyRepository.findById(companyId)
            .map(companyDtoConverter::toDto)
            .orElseThrow(() -> new IllegalArgumentException("Company does not exist"));
    }

    @Transactional
    public CompanyDto createCompany(CompanyDto newCompany, long userId) {
        var user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        var company = companyDtoConverter.toDomain(newCompany);
        var withId = companyRepository.save(company);
        user.getCompanies().add(company);
        userRepository.save(user);
        return companyDtoConverter.toDto(withId);
    }

    @Transactional
    public CompanyDto updateCompany(CompanyDto companyDto) {
        var company = companyRepository.findById(companyDto.getId())
            .orElseThrow(() -> new IllegalArgumentException("Company does not exist"));
        var updated = companyDtoConverter.toDomain(companyDto, company);
        var fromDb = companyRepository.save(updated);
        return companyDtoConverter.toDto(fromDb);
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
