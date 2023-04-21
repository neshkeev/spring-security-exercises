package com.luxoft.spingsecurity.anonymousauth.dto.converters;

import com.luxoft.spingsecurity.anonymousauth.dto.OrderDto;
import com.luxoft.spingsecurity.anonymousauth.model.Company;
import com.luxoft.spingsecurity.anonymousauth.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoConverter {

    public OrderDto toDto(Order domain) {
        return new OrderDto(domain.getId(), domain.getAmount());
    }

    public Order toDomain(OrderDto dto, Company customer) {
        var domain = new Order();
        domain.setId(dto.getId());
        domain.setAmount(dto.getAmount());
        domain.setCustomer(customer);
        return domain;
    }
}
