package com.luxoft.spingsecurity.acl.service;

import com.luxoft.spingsecurity.acl.dto.UserDto;
import com.luxoft.spingsecurity.acl.dto.converters.UserDtoConverter;
import com.luxoft.spingsecurity.acl.repository.UserRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
            .map(userDtoConverter::toDto)
            .collect(toList());
    }

    @Transactional(readOnly = true)
    public UserDto getById(long userId) {
        return userRepository.findById(userId)
            .map(userDtoConverter::toDto)
            .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
    }

    @Secured("ROLE_ADMIN")
    @Transactional
    public UserDto create(UserDto userDto) {
        var user = userDtoConverter.toDomain(userDto);
        var withId = userRepository.save(user);
        return userDtoConverter.toDto(withId);
    }

    @PreAuthorize("hasRole('ADMIN') or #userDto.login == authentication.name")
    @Transactional
    public UserDto update(UserDto userDto) {
        var user = userRepository.findById(userDto.getId())
            .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        var updated = userDtoConverter.toDomain(userDto, user);
        var fromDb = userRepository.save(updated);
        return userDtoConverter.toDto(fromDb);
    }
}
