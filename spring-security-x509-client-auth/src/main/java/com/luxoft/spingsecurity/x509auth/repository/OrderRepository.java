package com.luxoft.spingsecurity.x509auth.repository;

import com.luxoft.spingsecurity.x509auth.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
