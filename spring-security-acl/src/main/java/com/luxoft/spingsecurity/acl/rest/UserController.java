package com.luxoft.spingsecurity.acl.rest;

import com.luxoft.spingsecurity.acl.dto.UserDto;
import com.luxoft.spingsecurity.acl.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDto getById(@PathVariable("id") long userId) {
        return userService.getById(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PutMapping("/user")
    public UserDto update(@RequestBody UserDto userDto) {
        return userService.update(userDto);
    }
}
