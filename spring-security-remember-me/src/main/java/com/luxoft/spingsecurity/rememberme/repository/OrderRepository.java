package com.luxoft.spingsecurity.rememberme.repository;

import com.luxoft.spingsecurity.rememberme.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
