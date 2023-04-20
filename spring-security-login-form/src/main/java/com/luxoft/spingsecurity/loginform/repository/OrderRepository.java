package com.luxoft.spingsecurity.loginform.repository;

import com.luxoft.spingsecurity.loginform.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
