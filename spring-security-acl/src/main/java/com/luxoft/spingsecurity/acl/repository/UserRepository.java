package com.luxoft.spingsecurity.acl.repository;

import com.luxoft.spingsecurity.acl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.roles where u.login = :login ")
    Optional<User> fetchByLogin(@Param("login") String login);

    @PostAuthorize("hasRole('ADMIN') or returnObject.orElse(null)?.login == authentication.name")
    @Override
    Optional<User> findById(Long aLong);
}
