package com.luxoft.spingsecurity.basicauth.repository;

import com.luxoft.spingsecurity.basicauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
