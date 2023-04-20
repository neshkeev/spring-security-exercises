package com.luxoft.spingsecurity.basicauth.repository;

import com.luxoft.spingsecurity.basicauth.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
