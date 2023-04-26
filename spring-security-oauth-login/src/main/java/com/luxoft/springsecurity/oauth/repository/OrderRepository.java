package com.luxoft.springsecurity.oauth.repository;

import com.luxoft.springsecurity.oauth.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
