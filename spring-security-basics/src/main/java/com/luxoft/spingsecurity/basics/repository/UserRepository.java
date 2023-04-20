package com.luxoft.spingsecurity.basics.repository;

import com.luxoft.spingsecurity.basics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
