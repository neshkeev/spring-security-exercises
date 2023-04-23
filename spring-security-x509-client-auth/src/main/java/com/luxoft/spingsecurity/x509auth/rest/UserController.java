package com.luxoft.spingsecurity.x509auth.rest;

import com.luxoft.spingsecurity.x509auth.dto.UserDto;
import com.luxoft.spingsecurity.x509auth.dto.converters.UserDtoConverter;
import com.luxoft.spingsecurity.x509auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserDtoConverter converter;

    public UserController(UserRepository userRepository, UserDtoConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    @GetMapping("/user")
    public UserDto loginPage(Principal principal) {
        return userRepository.fetchByLogin(principal.getName())
                .map(converter::toDto)
                .orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
    }
}
