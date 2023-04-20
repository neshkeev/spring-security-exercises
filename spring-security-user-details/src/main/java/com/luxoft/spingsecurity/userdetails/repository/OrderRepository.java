package com.luxoft.spingsecurity.userdetails.repository;

import com.luxoft.spingsecurity.userdetails.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
