package com.luxoft.spingsecurity.basics.repository;

import com.luxoft.spingsecurity.basics.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
