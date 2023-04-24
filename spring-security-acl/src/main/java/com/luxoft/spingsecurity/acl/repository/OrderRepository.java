package com.luxoft.spingsecurity.acl.repository;

import com.luxoft.spingsecurity.acl.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
