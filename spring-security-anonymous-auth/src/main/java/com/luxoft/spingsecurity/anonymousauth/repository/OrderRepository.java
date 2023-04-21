package com.luxoft.spingsecurity.anonymousauth.repository;

import com.luxoft.spingsecurity.anonymousauth.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
