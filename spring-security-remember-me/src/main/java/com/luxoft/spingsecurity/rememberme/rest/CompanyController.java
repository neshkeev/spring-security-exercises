package com.luxoft.spingsecurity.rememberme.rest;

import com.luxoft.spingsecurity.rememberme.dto.CompanyDto;
import com.luxoft.spingsecurity.rememberme.dto.OrderDto;
import com.luxoft.spingsecurity.rememberme.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company")
    public List<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @GetMapping(value = "/company", params = "user-id")
    public List<CompanyDto> getAllByUser(@RequestParam("user-id") long userId) {
        return companyService.getAllByUserId(userId);
    }

    @GetMapping("/company/{id}")
    public CompanyDto getById(@PathVariable("id") long companyId) {
        return companyService.getById(companyId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/company")
    public CompanyDto create(
        @RequestBody CompanyDto newCompany,
        @RequestParam("user-id") long userId
    ) {
        return companyService.createCompany(newCompany, userId);
    }

    @PutMapping("/company")
    public CompanyDto update(@RequestBody CompanyDto companyDto) {
        return companyService.updateCompany(companyDto);
    }

    @GetMapping("/company/{id}/order")
    public List<OrderDto> getCompanyOrders(@PathVariable("id") long companyId) {
        return companyService.getCompanyOrders(companyId);
    }

    @PostMapping("/company/{id}/order")
    public OrderDto createOrder(
        @PathVariable("id") long companyId,
        @RequestBody OrderDto orderDto
    ) {
        return companyService.createOrder(companyId, orderDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/company/{cid}/order/{oid}")
    public void deleteOrder(
        @PathVariable("oid") long orderId
    ) {
        companyService.deleteOrder(orderId);
    }
}
