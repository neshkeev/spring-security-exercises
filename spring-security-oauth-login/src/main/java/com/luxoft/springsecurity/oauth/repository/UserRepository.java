package com.luxoft.springsecurity.oauth.repository;

import com.luxoft.springsecurity.oauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @PostAuthorize("hasRole('ADMIN') or returnObject.orElse(null)?.login == authentication.name")
    @Override
    Optional<User> findById(Long aLong);
}
